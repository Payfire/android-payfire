package com.payfire.database.model.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val name: String,
    val image: String,
    val price: Double
) {
    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0
}