package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.User
import com.clovertech.autolib.repository.ProfileRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    val responseProfile: MutableLiveData<Response<User>> = MutableLiveData()


    fun getThisProfile(id: Int) {
        viewModelScope.launch {
            val response: Response<User> = ProfileRepo.getProfile(id)
            responseProfile.value = response
        }
    }


}