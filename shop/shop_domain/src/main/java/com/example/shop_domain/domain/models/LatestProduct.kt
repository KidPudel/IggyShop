package com.example.shop_domain.domain.models

data class LatestProduct(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
)