package com.clovertech.autolib.repository

import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.network.client.AuthApiClient
import retrofit2.Response

object LoginRepo {

    suspend fun userLogin(login: Login) : Response<Token> {
        return AuthApiClient.authApiService.getUserByInfo(login)
    }

}