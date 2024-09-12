package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.repository.NotificationRepo
import com.clovertech.autolib.model.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationViewModel : ViewModel() {

    val notificationList = MutableLiveData<List<Notification>>()

    fun postFCMToken (context: Context, agentToken: AgentToken) {
        NotificationRepo.postFCMToken(context, agentToken)
    }

    fun getAllNotifications(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            val response = NotificationRepo.getAllNotifications(context)
            withContext(Dispatchers.Main){
                notificationList.value = response
            }
        }
    }

    fun fetchAllNotifications(context: Context){
        viewModelScope.launch{
            NotificationRepo.fetchAllNotifications(context)
        }
    }

}