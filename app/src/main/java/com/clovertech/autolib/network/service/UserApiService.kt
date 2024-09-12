package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.ResponseAgent
import com.clovertech.autolib.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users/{id}")
    suspend fun getUserById(@Path("id")id: Int): Response<User>

    @GET("agents/{id}")
    suspend fun getAgentById(@Path("id")id: Int): Response<ResponseAgent>
}