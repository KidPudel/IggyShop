package com.example.shop_data.data.remote.dto

import com.example.shop_domain.domain.models.LatestProduct

data class LatestGoodsDto(
    val latest: List<LatestProductDto>
) {
    companion object {
        /**
         * returns dummy instance on unsuccessful response
         */
        fun onBadResponse(): List<LatestProduct> {
            return listOf(
                LatestProduct(
                    category = "error",
                    image_url = "error",
                    name = "error",
                    price = 0
                )
            )
        }
    }
}