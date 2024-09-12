package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.MaterielApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MaterielApiClient {
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://volet-maintenance.herokuapp.com/service-materiel")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val materielApiService: MaterielApiService by lazy {
        retrofit.create(MaterielApiService::class.java)

    }
}