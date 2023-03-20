package com.example.shop_data.data.repositories

import com.example.shop_data.data.remote.IGoodsApi
import com.example.shop_data.data.remote.dto.FlashSaleGoodsDto
import com.example.shop_data.data.remote.dto.LatestGoodsDto
import com.example.shop_data.data.remote.dto.toFlashSaleProduct
import com.example.shop_data.data.remote.dto.toLatestProduct
import com.example.shop_domain.domain.irepositories.IGoodsRepository
import com.example.shop_domain.domain.models.FlashSaleProduct
import com.example.shop_domain.domain.models.LatestProduct
import javax.inject.Inject

class GoodsRepository @Inject constructor(private val goodsApi: IGoodsApi) : IGoodsRepository {

    override suspend fun getLatestGoods(): List<LatestProduct> {
        val response = goodsApi.getLatestGoods()
        var body: LatestGoodsDto? = null
        if (response.isSuccessful) {
            body = response.body()
        }
        val latestGoods = body?.latest?.map {it.toLatestProduct()}
        return latestGoods ?: LatestGoodsDto.onBadResponse()
    }

    override suspend fun getFlashSaleGoods(): List<FlashSaleProduct> {
        val response = goodsApi.getFlashSaleGoods()
        var body: FlashSaleGoodsDto? = null
        if (response.isSuccessful) {
            body = response.body()
        }
        val flashSaleGoods = body?.flash_sale?.map { it.toFlashSaleProduct() }
        return flashSaleGoods ?: FlashSaleGoodsDto.onBadResponse()
    }
}