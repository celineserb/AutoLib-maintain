package com.clovertech.autolib.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.clovertech.autolib.model.Step


@Dao
interface StepDAO {

    @androidx.room.Query("select * from steps")
    fun getStep(): Step

    @androidx.room.Query("select * from steps where isSynchronized=0")
    fun getStepToSynchronize(): Step

    @Insert
    fun addStep(vararg step: Step)

    @Update
    fun updateStep(step: Step)


}
