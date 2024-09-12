package com.clovertech.autolib.repository

import com.clovertech.autolib.model.Equipment
import com.clovertech.autolib.model.EquipmentToken
import com.clovertech.autolib.model.NewEquipment
import com.clovertech.autolib.network.client.EquipmentApiClient
import retrofit2.Response

object EquipmentRepo {

        suspend fun getAllEquipments(): Response<List<Equipment>> {
            return EquipmentApiClient.equipmentApiService.getAllEquipment()
        }

        suspend fun addNewEquipment(newEquipment: NewEquipment): Response<EquipmentToken> {
            return EquipmentApiClient.equipmentApiService.addNewUsedEquipment(newEquipment)
        }
}