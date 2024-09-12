package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agent")
data class Agent(
    var refPermis: Int = 0,
    var nom: String = "",
    var prenom: String = "",
    var adresse : String = "",
    var photoPersonnelle: String = ""
){
    @PrimaryKey(autoGenerate = true) var idUtilisateur: Int = 0
}

data class ResponseAgent(
    var refPermis: String = "",
    var idAgent: Int
)