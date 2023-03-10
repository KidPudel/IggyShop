package com.example.iggyshop.domain.use_cases

import com.example.iggyshop.data.remote.dto.toFlashSaleProduct
import com.example.iggyshop.domain.irepositories.IGoodsRepository
import com.example.iggyshop.domain.models.FlashSaleProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFlashSaleGoodsUseCase @Inject constructor(private val goodsRepository: IGoodsRepository) {
    suspend fun getFlashSaleGoods(): Flow<List<FlashSaleProduct>> = flow {
        val flashSaleGoodsDto = goodsRepository.getFlashSaleGoods()
        emit(value = flashSaleGoodsDto.map { it.toFlashSaleProduct() })
    }
}