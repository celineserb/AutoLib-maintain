package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Notifications")
data class Notification(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("idTask")
    var idTask: Int,
    //var titre: String,
    //var date: String,
    //var image: Int,
    @SerializedName("read")
    var lu :Boolean
    )