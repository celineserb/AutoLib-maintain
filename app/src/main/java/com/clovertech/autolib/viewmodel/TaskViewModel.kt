package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Task
import com.clovertech.autolib.repository.TaskRepo
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel : ViewModel() {

    var task: Task = Task(0, 0, "", "", 0, Date(), Date(),
            mutableListOf(), mutableListOf(), "")

    fun updateTask(context: Context, task: Task) {
        TaskRepo.updateTask(context, task)
    }


    fun getAllTasks(context: Context): LiveData<List<Task>>? {
        return TaskRepo.getAllTasks(context)
    }

    fun getTasksByIdAgent(context: Context, id: Int) {
        viewModelScope.launch {
            TaskRepo.getTaskByIdAgent(context, id)
        }
    }

}