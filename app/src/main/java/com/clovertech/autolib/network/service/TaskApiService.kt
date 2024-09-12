package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Task
import com.clovertech.autolib.model.TaskState
import retrofit2.Response
import retrofit2.http.*

interface TaskApiService {

    @GET("task/agent/{idAgent}")
    suspend fun getTasksById(@Path("idAgent") id: Int): Response<List<Task>>

    @PUT("taskState/{uuid}")
    suspend fun updateTaskState(
        @Path("uuid") uuid: String,
        @Body taskState: TaskState
    ): Response<String>

}