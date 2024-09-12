package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class Step(
    @PrimaryKey var idStep: Int,
    var step: String,
    var completed: Boolean,
    var isSynchronized: Int = 0

) {
}
