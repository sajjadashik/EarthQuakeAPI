package com.sajjad.earthquakeapi.viewomodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjad.earthquakeapi.models.EarthQuakeModel
import com.sajjad.earthquakeapi.repos.EarthQuakeRepository
import kotlinx.coroutines.launch

class EarthQuakeViewModel : ViewModel() {
    val repository = EarthQuakeRepository()

    val earthquakeLiveData: MutableLiveData<EarthQuakeModel> = MutableLiveData()

    fun fetchData(fromDate:String, toDate:String,unit:String) {
        viewModelScope.launch {
            try {
                earthquakeLiveData.value = repository.fetchCurrentData(fromDate,toDate,unit)
            }catch (e: Exception) {
                Log.d("EarthquakeViewModel", e.localizedMessage)
            }
        }
    }

}