package com.example.iggyshop.domain.irepositories

import com.example.iggyshop.data.remote.dto.FlashSaleProductDto
import com.example.iggyshop.data.remote.dto.LatestProductDto

interface IGoodsRepository {

    /**
     * retrieve latest goods from api
     */
    suspend fun getLatestGoods(): List<LatestProductDto>

    /**
     * retrieve flash sale goods from api
     */
    suspend fun getFlashSaleGoods(): List<FlashSaleProductDto>
}