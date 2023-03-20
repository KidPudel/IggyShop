package com.example.shop_data.data.remote.dto

import com.example.shop_domain.domain.models.FlashSaleProduct

data class FlashSaleProductDto(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)

fun FlashSaleProductDto.toFlashSaleProduct(): FlashSaleProduct {
    return FlashSaleProduct(
        category = this.category,
        discount = this.discount,
        image_url = this.image_url,
        name = this.name,
        price = this.price
    )
}
