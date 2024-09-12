package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "usedEquipment")
data class Materiel(

    @SerializedName("description") var description: String,
    @SerializedName("quantity") var quantity: String

) {
    @PrimaryKey(autoGenerate = true)
    var idEquipment: Int = 0
}