package com.example.iggyshop.data.remote.dto

import com.example.iggyshop.domain.models.LatestProduct

data class LatestProductDto(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) {
    companion object {
        /**
         * returns dummy instance on unsuccessful response
         */
        fun onBadResponse(): LatestProductDto {
            return LatestProductDto(
                category = "error",
                image_url = "error",
                name = "error",
                price = 0
            )
        }
    }
}

fun LatestProductDto.toLatestProduct(): LatestProduct {
    return LatestProduct(
        category = this.category,
        image_url = this.image_url,
        name = this.name,
        price = this.price
    )
}
