package com.clovertech.autolib.model

import com.google.gson.annotations.Expose

data class Equipment(
    @Expose var uuid: String,
    @Expose var createdAt: String,
    @Expose var updatedAt: String,
    @Expose var equipmentName: String,
    @Expose var unitPrice: String,
    @Expose var category: String,
    var usedEquipments: List<UsedEquipment>
) {
}