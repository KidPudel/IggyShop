package com.example.iggyshop.data.remote.dto

data class LatestProduct(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
)