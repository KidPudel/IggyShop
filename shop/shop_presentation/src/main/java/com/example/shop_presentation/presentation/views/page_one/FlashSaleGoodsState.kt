package com.example.shop_presentation.presentation.views.page_one

import com.example.shop_domain.domain.models.FlashSaleProduct

data class FlashSaleGoodsState(
    val flashSaleGoods: List<FlashSaleProduct> = emptyList()
)