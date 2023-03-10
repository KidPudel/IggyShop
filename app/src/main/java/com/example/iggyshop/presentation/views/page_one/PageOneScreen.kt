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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
                Spacer(Modifier.height(23.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
                        contentDescription = "burger button"
                    )
                    Text(
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
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        Row {
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



                Text(
                    text = "Latest",
                    fontFamily = Fonts.poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 11.dp),
                    textAlign = TextAlign.Start
                )
                LazyRow(state = lazyListState, userScrollEnabled = true, modifier = Modifier.padding(horizontal = 11.dp)) {
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