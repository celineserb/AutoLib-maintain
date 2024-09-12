package com.clovertech.autolib.model

import com.google.gson.annotations.SerializedName

data class PanneResponse (
    @SerializedName("dateNotifPanne") var dateNotifPanne: String,
    @SerializedName("idAgentSentNotif") var idAgentSentNotif: String?,
    @SerializedName("state") var state: String,
    @SerializedName("idVehicle") var idVehicle: String?,
    @SerializedName("description") var description: String,
    @SerializedName("severityLevel") var severityLevel: String?,
    @SerializedName("idPanne")var idPanne: String)
{

}