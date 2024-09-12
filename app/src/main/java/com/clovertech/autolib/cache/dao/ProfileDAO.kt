package com.clovertech.autolib.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.clovertech.autolib.model.Agent

@Dao
interface ProfileDAO {

    @Query("SELECT * FROM agent WHERE idUtilisateur LIKE :idAgent")
    fun getThisProfile(idAgent: Int): Agent

    @Update
    fun updateProfile(agent: Agent)

    @Delete
    fun deleteProfile(agent: Agent)
}