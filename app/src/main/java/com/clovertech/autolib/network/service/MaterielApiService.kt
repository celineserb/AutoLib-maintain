package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.Token
import retrofit2.Response
import retrofit2.http.*

interface MaterielApiService {

    @GET("/usedEquipment")
    suspend fun getAllMateriel(): Response<List<Materiel>>

    @POST("/usedEquipment")
    suspend fun addMateriel(@Body materiel: Materiel): Response<Materiel>

    @GET("/usedEquipment/{id}")
    suspend fun getMaterielById(@Path("id") id: Int): Response<Materiel>

    @PUT("/usedEquipment/{id}")
    suspend fun updateMateriel(@Path("id") id: Int
                               , @Body materiel: Materiel): Response<Materiel>

    @DELETE("/usedEquipment/{id}")
    suspend fun deleteMateriel(@Path("id") id: Int): Response<Token>

}