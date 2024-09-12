package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.PanneApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PanneApiClient {

    private val gson : Gson by lazy {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("http://3353aa3edbce.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val PANNE_API_SERVICE: PanneApiService by lazy {
        retrofit.create(PanneApiService::class.java)
    }
}