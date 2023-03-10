package com.example.iggyshop.presentation.views.page_one

import com.example.iggyshop.domain.models.FlashSaleProduct
import com.example.iggyshop.domain.models.LatestProduct

data class LatestGoodsState(
    val latestGoods: List<LatestProduct> = emptyList()
)