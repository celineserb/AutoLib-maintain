package com.clovertech.autolib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_users")
data class AuthUser (
    @PrimaryKey (autoGenerate = true) var idAuthUser: Int,
    @ColumnInfo (name = "type") var idUser: Int,
    @ColumnInfo (name = "adresseEmail")var email: String,
    @ColumnInfo (name = "motDePasse")var password: String
)