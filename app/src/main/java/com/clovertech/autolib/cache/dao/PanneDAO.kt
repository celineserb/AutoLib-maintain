package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clovertech.autolib.model.Panne

@Dao
interface PanneDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPanne(panne: Panne)

    @Delete
    suspend fun deletePanne(panne: Panne)

    @Query("SELECT * FROM pannes")
    fun getAllPannes(): LiveData<List<Panne>>

}