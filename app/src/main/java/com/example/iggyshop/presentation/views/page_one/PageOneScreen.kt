package com.example.iggyshop.presentation.views.page_one

import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.presentation.view_models.PageOneViewModel
import java.util.*

@Composable
fun PageOneScreen(navigationController: NavController) {
    val pageOneViewModel = hiltViewModel<PageOneViewModel>()
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    val country = "US"
    val language = "en"
    var currency: String
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyColors.ghostWhite)
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Page 1")
                LazyRow(state = lazyListState, userScrollEnabled = true) {
                    items(items = pageOneViewModel.latestGoodsState.value.latestGoods) {latestProduct ->
                        Card(shape = RoundedCornerShape(10.dp)) {
                            Box {
                                AsyncImage(
                                    model = latestProduct.image_url,
                                    contentDescription = "image of a product",
                                    modifier = Modifier.matchParentSize()
                                )
                                Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.Start) {
                                    Card(shape = RoundedCornerShape(50.dp)) {
                                        Text(text = latestProduct.category)
                                    }
                                    Text(text = latestProduct.name)
                                    currency = NumberFormat.getCurrencyInstance(Locale.US).format(latestProduct.price)
                                    Text(text = currency)
                                }
                            }
                        }
                    }

                }
            }

        }

    }
}