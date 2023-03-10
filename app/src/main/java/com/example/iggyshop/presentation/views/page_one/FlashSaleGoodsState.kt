package com.example.iggyshop.presentation.views.page_one

import com.example.iggyshop.domain.models.FlashSaleProduct

data class FlashSaleGoodsState(
    val flashSaleGoods: List<FlashSaleProduct> = emptyList()
)