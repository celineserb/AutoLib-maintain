package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.Token
import retrofit2.Response
import retrofit2.http.*


interface AuthApiService {

    @POST("signin")
    suspend fun getUserByInfo(@Body login: Login): Response<Token>

}