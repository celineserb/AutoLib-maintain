package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.UserApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object UserApiClient {

    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://project2cs2021.herokuapp.com/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

}