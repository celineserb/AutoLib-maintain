package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.AuthApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AuthApiClient {
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("http://54.37.87.85:7000/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)

    }


}