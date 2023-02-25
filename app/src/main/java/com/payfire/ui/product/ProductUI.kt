package com.payfire.ui.product

data class ProductUI(
    val name: String,
    val image: String,
    val price: Double,
) {
    var count: Int = 0
}