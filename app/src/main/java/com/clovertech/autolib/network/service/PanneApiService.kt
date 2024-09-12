package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.model.PanneResponse
import retrofit2.Response
import retrofit2.http.*

interface PanneApiService {

    @POST("/addPanne")
    suspend fun insertPanne(@Body panne: Panne): Response<PanneResponse>
}