package com.example.shop_domain.domain.use_cases

import com.example.shop_domain.domain.irepositories.IGoodsRepository
import com.example.shop_domain.domain.models.FlashSaleProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFlashSaleGoodsUseCase @Inject constructor(private val goodsRepository: IGoodsRepository) {
    suspend fun getFlashSaleGoods(): Flow<List<FlashSaleProduct>> = flow {
        val flashSaleGoodsDto = goodsRepository.getFlashSaleGoods()
        emit(value = flashSaleGoodsDto)
    }
}