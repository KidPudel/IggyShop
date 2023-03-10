package com.example.iggyshop.data.repositories

import com.example.iggyshop.data.remote.IGoodsApi
import com.example.iggyshop.data.remote.dto.FlashSaleProductDto
import com.example.iggyshop.data.remote.dto.LatestProductDto
import com.example.iggyshop.domain.irepositories.IGoodsRepository
import javax.inject.Inject

class GoodsRepository @Inject constructor(private val goodsApi: IGoodsApi) : IGoodsRepository {

    override suspend fun getLatestGoods(): List<LatestProductDto> {
        val response = goodsApi.getLatestGoods()
        var body: List<LatestProductDto>? = null
        if (response.isSuccessful) {
            body = response.body()
        }
        return body ?: listOf(LatestProductDto.onBadResponse())
    }

    override suspend fun getFlashSaleGoods(): List<FlashSaleProductDto> {
        val response = goodsApi.getFlashSaleGoods()
        var body: List<FlashSaleProductDto>? = null
        if (response.isSuccessful) {
            body = response.body()
        }
        return body ?: listOf(FlashSaleProductDto.onBadResponse())
    }
}