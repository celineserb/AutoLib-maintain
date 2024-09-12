package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Equipment
import com.clovertech.autolib.model.EquipmentToken
import com.clovertech.autolib.model.NewEquipment

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EquipmentApiService {

    @GET("service-materiel/equipment")
    suspend fun getAllEquipment(): Response<List<Equipment>>

    @POST("service-materiel/usedEquipment")
    suspend fun addNewUsedEquipment(@Body newEquipment: NewEquipment): Response<EquipmentToken>
}