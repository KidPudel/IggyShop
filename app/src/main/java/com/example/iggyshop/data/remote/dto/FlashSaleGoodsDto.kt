package com.example.iggyshop.data.remote.dto

data class FlashSaleGoodsDto(
    val flash_sale: List<FlashSaleProductDto>
) {
    companion object {
        /**
         * returns dummy instance on unsuccessful response
         */
        fun onBadResponse(): FlashSaleGoodsDto {
            return FlashSaleGoodsDto(
                flash_sale = listOf(
                    FlashSaleProductDto(
                        category = "error",
                        discount = 0,
                        image_url = "error",
                        name = "error",
                        price = 0.0
                    )
                )
            )
        }
    }
}