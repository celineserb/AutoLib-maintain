package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clovertech.autolib.model.TaskModel

@Dao
interface TaskModelDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTaskModel(taskModel: TaskModel)

    @Query("SELECT * FROM taskModel")
    fun getAllTasks(): LiveData<List<TaskModel>>
}