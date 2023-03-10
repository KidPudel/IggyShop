package com.example.iggyshop.domain.use_cases

import com.example.iggyshop.data.remote.dto.toLatestProduct
import com.example.iggyshop.domain.irepositories.IGoodsRepository
import com.example.iggyshop.domain.models.LatestProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLatestGoodsUseCase @Inject constructor(private val goodsRepository: IGoodsRepository) {
    suspend fun getLatestGoods(): Flow<List<LatestProduct>> = flow {
        val latestGoodsDto = goodsRepository.getLatestGoods()
        emit(value = latestGoodsDto.map { it.toLatestProduct() })
    }
}