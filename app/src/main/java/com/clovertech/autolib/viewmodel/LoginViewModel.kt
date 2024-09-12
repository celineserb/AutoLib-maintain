package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.ResponseAgent
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.model.User
import com.clovertech.autolib.repository.LoginRepo
import com.clovertech.autolib.repository.ProfileRepo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val loginResponse: MutableLiveData<Response<Token>> = MutableLiveData()
    val responseProfile: MutableLiveData<Response<User>> = MutableLiveData()
    val responseAgent: MutableLiveData<Response<ResponseAgent>> = MutableLiveData()

    fun onLoginButtonClick(login: Login) {
        viewModelScope.launch {
            val response: Response<Token> = LoginRepo.userLogin(login)
            loginResponse.value = response
        }

    }

    fun getThisProfile(id: Int) {
        viewModelScope.launch {
            val response: Response<User> = ProfileRepo.getProfile(id)
            responseProfile.value = response
        }
    }

    fun getAgent(id: Int){
        viewModelScope.launch {
            val response = ProfileRepo.getAgent(id)
            responseAgent.value = response
        }
    }

}