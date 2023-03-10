package com.example.iggyshop.domain.models

data class FlashSaleProduct(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)