package com.example.iggyshop.data.remote.dto

import com.example.iggyshop.domain.models.LatestProduct

data class LatestProductDto(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
)

fun LatestProductDto.toLatestProduct(): LatestProduct {
    return LatestProduct(
        category = this.category,
        image_url = this.image_url,
        name = this.name,
        price = this.price
    )
}
