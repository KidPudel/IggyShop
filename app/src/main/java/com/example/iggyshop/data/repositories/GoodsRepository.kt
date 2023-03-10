package com.example.iggyshop.data.repositories

import com.example.iggyshop.data.remote.IGoodsApi
import com.example.iggyshop.data.remote.dto.FlashSaleGoodsDto
import com.example.iggyshop.data.remote.dto.FlashSaleProductDto
import com.example.iggyshop.data.remote.dto.LatestGoodsDto
import com.example.iggyshop.data.remote.dto.LatestProductDto
import com.example.iggyshop.domain.irepositories.IGoodsRepository
import javax.inject.Inject

class GoodsRepository @Inject constructor(private val goodsApi: IGoodsApi) : IGoodsRepository {

    override suspend fun getLatestGoods(): LatestGoodsDto {
        val response = goodsApi.getLatestGoods()
        var body: LatestGoodsDto? = null
        if (response.isSuccessful) {
            body = response.body()
        }
        return body ?: LatestGoodsDto.onBadResponse()
    }

    override suspend fun getFlashSaleGoods(): FlashSaleGoodsDto {
        val response = goodsApi.getFlashSaleGoods()
        var body: FlashSaleGoodsDto? = null
        if (response.isSuccessful) {
            body = response.body()
        }
        return body ?: FlashSaleGoodsDto.onBadResponse()
    }
}