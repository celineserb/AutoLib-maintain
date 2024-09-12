package com.clovertech.autolib.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.model.PanneResponse
import com.clovertech.autolib.network.client.PanneApiClient
import retrofit2.Response

object PanneRepo {
        var appDb: AutolibDatabase? = null

        var pannes: LiveData<List<Panne>>? = null

        private fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }



        fun getAllPannes(context: Context): LiveData<List<Panne>>? {
            appDb = initializeDB(context)
            pannes = appDb!!.panneDao().getAllPannes()

            return pannes
        }


        suspend fun addPanne(panne:Panne): Response<PanneResponse> {
            return PanneApiClient.PANNE_API_SERVICE.insertPanne(panne)
        }
}