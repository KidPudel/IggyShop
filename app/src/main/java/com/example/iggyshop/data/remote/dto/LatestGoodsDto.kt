package com.example.iggyshop.data.remote.dto

data class LatestGoodsDto(
    val latest: List<LatestProductDto>
) {
    companion object {
        /**
         * returns dummy instance on unsuccessful response
         */
        fun onBadResponse(): LatestGoodsDto {
            return LatestGoodsDto(
                latest = listOf(
                    LatestProductDto(
                        category = "error",
                        image_url = "error",
                        name = "error",
                        price = 0
                    )
                )
            )
        }
    }
}