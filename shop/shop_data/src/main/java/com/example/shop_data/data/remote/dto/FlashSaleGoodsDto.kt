package com.example.shop_data.data.remote.dto

import com.example.shop_domain.domain.models.FlashSaleProduct

data class FlashSaleGoodsDto(
    val flash_sale: List<FlashSaleProductDto>
) {
    companion object {
        /**
         * returns dummy instance on unsuccessful response
         */
        fun onBadResponse(): List<FlashSaleProduct> {
            return listOf(
                FlashSaleProduct(
                    category = "error",
                    discount = 0,
                    image_url = "error",
                    name = "error",
                    price = 0.0
                )
            )
        }
    }
}