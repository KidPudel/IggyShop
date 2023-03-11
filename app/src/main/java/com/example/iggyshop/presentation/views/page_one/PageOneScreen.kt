package com.example.iggyshop.presentation.views.page_one

import android.graphics.Paint
import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import java.util.*

@Composable
fun PageOneScreen(navigationController: NavController) {
    val pageOneViewModel = hiltViewModel<PageOneViewModel>()
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    val searchState = remember { mutableStateOf(value = "") }

    val country = "US"
    val language = "en"
    var currency: String

    val categories: List<Int> = listOf(
        R.drawable.ic_phone,
        R.drawable.ic_headphones,
        R.drawable.ic_controller,
        R.drawable.ic_car,
        R.drawable.ic_sleep,
        R.drawable.ic_robot
    )

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyColors.ghostWhite),
        topBar = {
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
                    Icon(
                        modifier = Modifier.padding(bottom = 15.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
                        contentDescription = "burger button"
                    )
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
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        LocalTextStyle provides MaterialTheme.typography.h5
                    ) {

                    }
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
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
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
                        Card(
                            modifier = Modifier
                                .width(42.11.dp)
                                .height(38.dp),
                            backgroundColor = MyColors.antiFlashWhite,
                            shape = RoundedCornerShape(50.dp),
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = category),
                                contentDescription = "category",
                                modifier = Modifier.padding(horizontal = 13.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                }
                Row {
                    Spacer(modifier = Modifier.width(11.dp))
                    Text(
                        text = "Latest",
                        fontFamily = Fonts.poppinsFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                }

                Row {
                    Spacer(modifier = Modifier.width(11.dp))
                    LazyRow(
                        state = lazyListState,
                        userScrollEnabled = true,
                    ) {
                        items(items = pageOneViewModel.latestGoodsState.value.latestGoods) { latestProduct ->
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .width(114.dp)
                                    .height(149.dp)
                            ) {
                                Box {
                                    AsyncImage(
                                        model = latestProduct.image_url,
                                        contentDescription = "image of a product",
                                        modifier = Modifier.matchParentSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.Bottom,
                                        horizontalAlignment = Alignment.Start,
                                        modifier = Modifier.matchParentSize()
                                    ) {
                                        Card(shape = RoundedCornerShape(50.dp)) {
                                            Text(
                                                text = latestProduct.category,
                                                fontFamily = Fonts.poppinsFamily,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.Black,
                                                fontSize = 6.sp,
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                        Text(
                                            text = latestProduct.name,
                                            fontFamily = Fonts.poppinsFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.White,
                                            fontSize = 9.sp,
                                            textAlign = TextAlign.Start
                                        )
                                        currency = NumberFormat.getCurrencyInstance(Locale.US)
                                            .format(latestProduct.price)
                                        Text(
                                            text = currency,
                                            fontFamily = Fonts.poppinsFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.White,
                                            fontSize = 7.sp,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                        }

                    }

                }
            }

        }

    }
}