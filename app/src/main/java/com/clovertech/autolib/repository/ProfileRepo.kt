package com.clovertech.autolib.repository

import com.clovertech.autolib.model.ResponseAgent
import com.clovertech.autolib.model.User
import com.clovertech.autolib.network.client.UserApiClient
import retrofit2.Call
import retrofit2.Response

object ProfileRepo {
     suspend fun getProfile(id:Int): Response<User>{
         return UserApiClient.userApiService.getUserById(id)
     }

    suspend fun getAgent(id: Int): Response<ResponseAgent> {
        return UserApiClient.userApiService.getAgentById(id)
    }
}