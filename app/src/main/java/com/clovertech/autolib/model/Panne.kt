package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pannes")
data class Panne(

    @SerializedName("dateNotifPanne") var dateNotifPanne: String,
    @SerializedName("idAgentSentNotif") var idAgentSentNotif: Int?,
    @SerializedName("state") var state: String,
    @SerializedName("idVehicle") var idVehicle: Int,
    @SerializedName("description") var description: String,
    @SerializedName("severityLevel") var severityLevel: Int?

) {
@PrimaryKey var id:Int = 0
}