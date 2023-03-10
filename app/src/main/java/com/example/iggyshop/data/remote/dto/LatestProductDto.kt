package com.example.iggyshop.data.remote.dto

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
