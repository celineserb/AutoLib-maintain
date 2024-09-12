package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskModelTokens")
data class TaskModelToken(
    @PrimaryKey var id: Int,
    var taskModelName: String
) {
}