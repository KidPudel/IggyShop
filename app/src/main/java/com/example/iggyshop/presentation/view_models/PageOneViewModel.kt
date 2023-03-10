package com.example.iggyshop.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iggyshop.domain.use_cases.GetFlashSaleGoodsUseCase
import com.example.iggyshop.domain.use_cases.GetLatestGoodsUseCase
import com.example.iggyshop.presentation.views.page_one.FlashSaleGoodsState
import com.example.iggyshop.presentation.views.page_one.LatestGoodsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageOneViewModel @Inject constructor(
    private val getLatestGoodsUseCase: GetLatestGoodsUseCase,
    private val getFlashSaleGoodsUseCase: GetFlashSaleGoodsUseCase
) : ViewModel() {
    // store/track data in state
    private val _latestGoodsState = mutableStateOf(value = LatestGoodsState())
    val latestGoodsState: State<LatestGoodsState>
        get() = _latestGoodsState
    private val _flashSaleGoodsState = mutableStateOf(value = FlashSaleGoodsState())
    val flashSaleGoodsState: State<FlashSaleGoodsState>
        get() = _flashSaleGoodsState

    init {
        getGoods()
    }

    // get data
    private fun getGoods() {
        viewModelScope.launch(Dispatchers.IO) {
            getLatestGoodsUseCase.getLatestGoods().collectLatest {
                _latestGoodsState.value = LatestGoodsState(latestGoods = it)
            }
            getFlashSaleGoodsUseCase.getFlashSaleGoods().collectLatest {
                _flashSaleGoodsState.value = FlashSaleGoodsState(flashSaleGoods = it)
            }
        }
    }
}