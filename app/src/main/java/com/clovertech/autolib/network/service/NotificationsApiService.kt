package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.model.Notification
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationsApiService {

    @POST("service-taskNotif/ATNotif")
    suspend fun postToken(@Body agentToken: AgentToken): Response<Object>


    @GET("service-taskNotif/taskNotif")
    suspend fun getAllNotifications(): Response<List<Notification>>
}