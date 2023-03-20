package com.example.shop_domain.domain.use_cases

import com.example.shop_domain.domain.irepositories.IGoodsRepository
import com.example.shop_domain.domain.models.LatestProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLatestGoodsUseCase @Inject constructor(private val goodsRepository: IGoodsRepository) {
    suspend fun getLatestGoods(): Flow<List<LatestProduct>> = flow {
        val latestGoodsDto = goodsRepository.getLatestGoods()
        emit(value = latestGoodsDto)
    }
}