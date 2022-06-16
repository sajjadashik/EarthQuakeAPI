package com.sajjad.earthquakeapi.repos

import com.sajjad.earthquakeapi.models.EarthQuakeModel
import com.sajjad.earthquakeapi.network.NetworkService

class EarthQuakeRepository {
    suspend fun fetchCurrentData(startTime:String,endTime:String,unit:String): EarthQuakeModel {
        val endUrl =
            "query?format=geojson&starttime=$startTime&endtime=$endTime&minmagnitude=$unit"
        print(endUrl)
        return NetworkService.earthQuakeAPI
            .getEarthQuake(endUrl)
    }
}