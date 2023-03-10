package com.example.iggyshop.data.remote.dto

import com.example.iggyshop.domain.models.FlashSaleProduct

data class FlashSaleProductDto(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
) {
    companion object {
        /**
         * returns dummy instance on unsuccessful response
         */
        fun onBadResponse(): FlashSaleProductDto {
            return FlashSaleProductDto(
                category = "error",
                discount = 0,
                image_url = "error",
                name = "error",
                price = 0.0
            )
        }
    }
}

fun FlashSaleProductDto.toFlashSaleProduct(): FlashSaleProduct {
    return FlashSaleProduct(
        category = this.category,
        discount = this.discount,
        image_url = this.image_url,
        name = this.name,
        price = this.price
    )
}
