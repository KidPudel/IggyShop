package com.example.shop_domain.domain.irepositories

import com.example.shop_domain.domain.models.FlashSaleProduct
import com.example.shop_domain.domain.models.LatestProduct

interface IGoodsRepository {

    /**
     * retrieve latest goods from api
     */
    suspend fun getLatestGoods(): List<LatestProduct>

    /**
     * retrieve flash sale goods from api
     */
    suspend fun getFlashSaleGoods(): List<FlashSaleProduct>
}