package com.clovertech.autolib.model

import com.google.gson.annotations.SerializedName

data class AgentToken(
    @SerializedName("idAgent") var idAgent: Int,
    @SerializedName("token") var token: String
)
