package com.example.shop_presentation.presentation.views.page_one


import com.example.shop_domain.domain.models.LatestProduct

data class LatestGoodsState(
    val latestGoods: List<LatestProduct> = emptyList()
)