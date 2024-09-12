package com.clovertech.autolib.model

import com.google.gson.annotations.SerializedName

data class Vehicle(

    @SerializedName("idVehicle") val idVehicle: Int,
    @SerializedName("vehiclebrand") val vehiclebrand: String,
    @SerializedName("vehiclemodel") val vehiclemodel: String,
    @SerializedName("image") val image: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("chassisNumber") val chassisNumber: String)