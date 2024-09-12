package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Tasks")
data class Task(

    @Expose var idAgent: Int,
    @Expose @SerializedName("idVehicle")  var idVehicule: Int,
    @Expose var description: String,
    @Expose var taskTitle: String,
    @Expose var idTaskState: Int,
    @Expose var assignmentDate: Date,
    @Expose var endDate: Date?,
    @SerializedName("usedEquipments") var usedEquipements: List<Materiel>?,
    @Expose var steps: List<Step>?,
    @Expose @PrimaryKey var uuid: String,
    var isSync: Int = 0

) {

}

