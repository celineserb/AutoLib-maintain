package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.VehicleApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object VehicleApiClient {

    private val gson : Gson by lazy {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }
    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("http://54.37.87.85:7000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val vehicleService: VehicleApiService by lazy {
        retrofit.create(VehicleApiService::class.java)
    }
}