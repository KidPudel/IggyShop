package com.example.shop_data.data.remote

import com.example.shop_data.data.remote.dto.FlashSaleGoodsDto
import com.example.shop_data.data.remote.dto.LatestGoodsDto
import retrofit2.Response
import retrofit2.http.GET


/**
 * Api routes app access
 */
interface IGoodsApi {
    /**
     * get latest goods by navigating to https://designer.mocky.io/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7
     */
    @GET(value = "cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestGoods(): Response<LatestGoodsDto>

    /**
     * get flash sale goods by navigating to https://designer.mocky.io/v3/a9ceeb6e-416d-4352-bde6-2203416576ac
     */
    @GET(value = "a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleGoods(): Response<FlashSaleGoodsDto>
}