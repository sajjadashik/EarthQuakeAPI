package com.sajjad.earthquakeapi

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.earthquakeapi.adapter.EarthQuakeAdapter
import com.sajjad.earthquakeapi.databinding.FragmentHomeBinding
import com.sajjad.earthquakeapi.viewomodels.EarthQuakeViewModel
import com.tanvir.training.actioninputsbatch04.customdialogs.DatePickerFragment

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val earthquakeViewModel: EarthQuakeViewModel by activityViewModels()
    private var fromSelectedDate = "2022-01-20"
    private var toSelectedDate = "2022-06-15"
    private var unit = "5"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        initUnitSpinner()
        val adapter = EarthQuakeAdapter()
        binding.listRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.listRV.adapter = adapter
        earthquakeViewModel.fetchData(fromSelectedDate,toSelectedDate,unit)
        earthquakeViewModel.earthquakeLiveData.observe(viewLifecycleOwner) {data->
            Log.d("Data", "${data.features.size}")
            adapter.submitList(data.features)
        }
        binding.fromBtn.setOnClickListener {
            DatePickerFragment {
                fromSelectedDate = it
                binding.fromBtn.text = it
            }.show(childFragmentManager, null)
        }
        binding.toBtn.setOnClickListener {
            DatePickerFragment {
                toSelectedDate = it
                binding.toBtn.text = it
            }.show(childFragmentManager, null)
        }
        binding.goBtn.setOnClickListener {
            earthquakeViewModel.fetchData(fromSelectedDate,toSelectedDate,unit)
        }
        earthquakeViewModel.earthquakeLiveData.observe(viewLifecycleOwner) {data->
            Log.d("Data", "${data.features.size}")
            adapter.submitList(data.features)
        }

        return binding.root
    }

    private fun initUnitSpinner() {
        val unitAdapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_spinner_dropdown_item,
            unitList
        )
        binding.spinner.adapter = unitAdapter

        binding.spinner.onItemSelectedListener =

            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    unit = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
    }

}

val unitList = listOf("1","2","3","4","5","6","7","8")