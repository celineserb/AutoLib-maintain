package com.clovertech.autolib.repository

import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.network.client.MaterielApiClient
import retrofit2.Response

object MaterielRepo {

        suspend fun getAllEquipments(): Response<List<Materiel>> {
            return MaterielApiClient.materielApiService.getAllMateriel()
        }

        suspend fun addMateriel(materiel: Materiel): Response<Materiel> {
            return MaterielApiClient.materielApiService.addMateriel(materiel)
        }

        suspend fun getMaterielById(id: Int): Response<Materiel> {
            return MaterielApiClient.materielApiService.getMaterielById(id)
        }

        suspend fun updateMateriel(id: Int, materiel: Materiel): Response<Materiel> {
            return MaterielApiClient.materielApiService.updateMateriel(id, materiel)
        }

        suspend fun deleteMateriel(id: Int): Response<Token> {
            return MaterielApiClient.materielApiService.deleteMateriel(id)
        }

}