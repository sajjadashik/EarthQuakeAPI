package com.sajjad.earthquakeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.earthquakeapi.databinding.RowBinding
import com.sajjad.earthquakeapi.models.EarthQuakeModel


class EarthQuakeAdapter :ListAdapter<EarthQuakeModel.Feature, EarthQuakeAdapter.EarthQuakeViewHolder>(EarthQuakeDiffUtil()){

    class EarthQuakeViewHolder(val binding: RowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EarthQuakeModel.Feature.Properties) {
            binding.earth =item
        }
    }

    class EarthQuakeDiffUtil : DiffUtil.ItemCallback<EarthQuakeModel.Feature>() {
        override fun areItemsTheSame(
            oldItem: EarthQuakeModel.Feature,
            newItem: EarthQuakeModel.Feature
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: EarthQuakeModel.Feature,
            newItem: EarthQuakeModel.Feature
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeViewHolder {
        val binding = RowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EarthQuakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EarthQuakeViewHolder, position: Int) {
        val earthQuakeModel= getItem(position)
        holder.bind(earthQuakeModel.properties)
    }

}