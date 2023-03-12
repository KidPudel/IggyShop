package com.example.iggyshop.presentation.views.page_one

import android.graphics.Paint
import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.iggyshop.R
import com.example.iggyshop.common.Fonts
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.presentation.view_models.PageOneViewModel
import com.example.iggyshop.presentation.views.CustomTextField
import com.example.iggyshop.presentation.views.MyBottomBar
import java.util.*

@Composable
fun PageOneScreen(navigationController: NavController) {
    val pageOneViewModel = hiltViewModel<PageOneViewModel>()
    val lazyLatestState = rememberLazyListState()
    val lazyFlashSaleState = rememberLazyListState()
    val lazyDummyState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    val scrollState = rememberScrollState()

    val searchState = remember { mutableStateOf(value = "") }

    var currency: String

    val categories: Map<Int, String> = mapOf(
        R.drawable.ic_phone to "Phones",
        R.drawable.ic_headphones to "Headphones",
        R.drawable.ic_controller to "Games",
        R.drawable.ic_car to "Cars",
        R.drawable.ic_sleep to "Furniture",
        R.drawable.ic_robot to "Kids"
    )


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            PageOneTopBar()
        },
        bottomBar = {
            MyBottomBar(navigationController = navigationController)
        },
        backgroundColor = MyColors.ghostWhite
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(paddingValues = scaffoldPadding).background(color = MyColors.ghostWhite)) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState, enabled = true),
            ) {

                CustomTextField(
                    state = searchState,
                    placeholder = "What are you looking for?",
                    vectorResource = R.drawable.ic_search,
                    fontFamily = Fonts.poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    modifier = Modifier
                        .width(262.dp)
                        .height(24.dp)
                        .background(color = MyColors.whiteSmoke, shape = RoundedCornerShape(35.dp))
                )
                Spacer(Modifier.height(17.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(15.dp))
                    for (category in categories) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Card(
                                modifier = Modifier
                                    .width(42.11.dp)
                                    .height(38.dp),
                                backgroundColor = MyColors.antiFlashWhite,
                                shape = RoundedCornerShape(50.dp),
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = category.key),
                                    contentDescription = "category",
                                    modifier = Modifier.padding(horizontal = 13.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(11.dp))
                            Text(
                                text = category.value,
                                fontFamily = Fonts.poppinsFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 8.sp,
                                color = Color.LightGray
                            )

                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                }
                Spacer(modifier = Modifier.height(29.dp))

                // latest list
                Goods(
                    productType = "Latest",
                    lazyListState = lazyLatestState,
                    latestGoodsState = pageOneViewModel.latestGoodsState.value
                )

                Spacer(modifier = Modifier.height(17.dp))

                // flash sale list
                Goods(
                    productType = "Flash Sale",
                    lazyListState = lazyFlashSaleState,
                    flashSaleGoodsState = pageOneViewModel.flashSaleGoodsState.value
                )
                Spacer(modifier = Modifier.height(17.dp))

                // brand (dummy) list
                Goods(productType = "Brands", lazyDummyState)

            }
        }
    }
}

@Composable
private fun Goods(
    productType: String,
    lazyListState: LazyListState,
    latestGoodsState: LatestGoodsState? = null,
    flashSaleGoodsState: FlashSaleGoodsState? = null,
) {
    // list of colors instead of data for the last list of brands (api hasn't been given)
    val dummyList =
        listOf(MyColors.midnightGreen, MyColors.resedaGreen, MyColors.chocolateCosmos)

    var currency: String
    // title + view all
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(11.dp))
        Text(
            text = productType,
            fontFamily = Fonts.poppinsFamily,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )
        Text(
            text = "View all",
            fontFamily = Fonts.poppinsFamily,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = Modifier
                .weight(1f)
                .clickable {},
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.width(10.dp))
    }
    // goods
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(11.dp))
        LazyRow(state = lazyListState, userScrollEnabled = true) {
            if (latestGoodsState != null) {
                // latest part
                items(items = latestGoodsState.latestGoods) { latestProduct ->
                    Card(
                        shape = RoundedCornerShape(9.dp),
                        modifier = Modifier
                            .width(114.dp)
                            .height(149.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = latestProduct.image_url,
                                contentDescription = latestProduct.name,
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Card(
                                    shape = RoundedCornerShape(50.dp),
                                    backgroundColor = MyColors.timberWolf.copy(alpha = 0.85f),
                                    modifier = Modifier
                                        .width(35.dp)
                                        .height(12.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = latestProduct.category,
                                            fontFamily = Fonts.poppinsFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black,
                                            fontSize = 6.sp,
                                        )

                                    }
                                }
                                Text(
                                    text = latestProduct.name,
                                    fontFamily = Fonts.poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    fontSize = 9.sp,
                                )
                                currency = NumberFormat.getCurrencyInstance(Locale.US)
                                    .format(latestProduct.price)
                                Text(
                                    text = currency,
                                    fontFamily = Fonts.poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    fontSize = 7.sp,
                                )
                            }
                        }
                    }
                }
            } else if (flashSaleGoodsState != null) {
                // flash sale part
                items(items = flashSaleGoodsState.flashSaleGoods) { flashSaleProduct ->
                    Card(
                        shape = RoundedCornerShape(35.dp),
                        modifier = Modifier
                            .width(174.dp)
                            .height(221.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = flashSaleProduct.image_url,
                                contentDescription = flashSaleProduct.name,
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Card(
                                    shape = RoundedCornerShape(35.dp),
                                    backgroundColor = MyColors.timberWolf.copy(alpha = 0.85f),
                                    modifier = Modifier
                                        .width(49.58.dp)
                                        .height(15.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = flashSaleProduct.category,
                                            fontFamily = Fonts.poppinsFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black,
                                            fontSize = 9.sp,
                                        )
                                    }
                                }
                                Text(
                                    text = flashSaleProduct.name,
                                    fontFamily = Fonts.poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    fontSize = 13.sp,
                                )
                                currency = NumberFormat.getCurrencyInstance(Locale.US)
                                    .format(flashSaleProduct.price)
                                Text(
                                    text = currency,
                                    fontFamily = Fonts.poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    fontSize = 10.sp,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
            } else {
                // brand part
                items(items = dummyList) { myColor ->
                    Card(
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .width(114.dp)
                            .height(149.dp),
                    ) {
                        Box(
                            modifier = Modifier.background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        myColor,
                                        Color.Black
                                    ),
                                    startY = 20f
                                )
                            )
                        ) {

                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}

@Composable
private fun PageOneTopBar() {
    TopAppBar(
        backgroundColor = MyColors.ghostWhite,
        elevation = 0.dp,
        modifier = Modifier.height(80.dp),
    ) {
        Spacer(Modifier.height(23.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 23.dp)
        ) {
            // right side
            Icon(
                modifier = Modifier.padding(bottom = 15.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
                contentDescription = "burger button"
            )
            // custom text builder (center)
            Text(
                modifier = Modifier.padding(bottom = 15.dp),
                text = buildAnnotatedString {
                    append(text = "Trade by ")
                    withStyle(
                        style = SpanStyle(
                            color = MyColors.iris
                        )
                    ) {
                        append(text = "bata")
                    }
                },
                fontFamily = Fonts.montserratFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            // left size
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .height(30.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
                Spacer(modifier = Modifier.height(7.dp))
                Row(modifier = Modifier.fillMaxHeight()) {
                    Text(
                        text = "Location",
                        fontFamily = Fonts.poppinsFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                        contentDescription = "arrow down",
                        modifier = Modifier.offset(y = (5).dp)
                    )
                }
            }
        }
    }
}
