package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Equipment
import com.clovertech.autolib.model.EquipmentToken
import com.clovertech.autolib.model.NewEquipment
import com.clovertech.autolib.repository.EquipmentRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class EquipmentViewModel : ViewModel() {

    val response: MutableLiveData<Response<EquipmentToken>> = MutableLiveData()
    val responseEquipment: MutableLiveData<Response<List<Equipment>>> = MutableLiveData()


    fun addMateriel(newEquipment: NewEquipment) {
        viewModelScope.launch {
            val response: Response<EquipmentToken> = EquipmentRepo.addNewEquipment(newEquipment)
            this@EquipmentViewModel.response.value = response
        }

    }

    fun getAllEquipment() {
        viewModelScope.launch {
            val response = EquipmentRepo.getAllEquipments()
            responseEquipment.value = response
        }
    }

}