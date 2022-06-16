package com.sajjad.earthquakeapi.network

import com.sajjad.earthquakeapi.models.EarthQuakeModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.text.SimpleDateFormat
import java.util.*

const val base_url="https://earthquake.usgs.gov/fdsnws/event/1/"

fun getFormattedDate(dt: Long, pattern: String) : String {
    return SimpleDateFormat(pattern).format(Date(dt * 1000))
}


val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface EarthQuakeAPI {

    @GET
    suspend fun getEarthQuake(@Url endUrl: String) : EarthQuakeModel
}

object NetworkService {
    val earthQuakeAPI: EarthQuakeAPI by lazy {
        retrofit.create(EarthQuakeAPI::class.java)
    }
}

