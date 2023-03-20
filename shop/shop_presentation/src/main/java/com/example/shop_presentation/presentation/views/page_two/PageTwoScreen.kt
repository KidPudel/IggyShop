package com.example.shop_presentation.presentation.views.page_two

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shop_presentation.R
import com.example.shop_presentation.presentation.common.Fonts
import com.example.shop_presentation.presentation.common.MyColors
import com.example.shop_presentation.presentation.views.MyBottomBar
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun PageTwoScreen(navigationController: NavController, image: String, name: String) {
    val scaffoldState = rememberScaffoldState()
    val decodedImage = URLDecoder.decode(image, StandardCharsets.UTF_8.toString())

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { navigationController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigation_back),
                            contentDescription = "navigation back icon"
                        )

                    }
                }
            )
        },
        bottomBar = {
            MyBottomBar(navigationController = navigationController)
        }
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // image with panel
                Box {
                    Row {
                        Card(
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .width(328.dp)
                                .height(279.dp)
                        ) {
                            AsyncImage(
                                model = decodedImage,
                                contentDescription = name,
                                contentScale = ContentScale.Crop
                            )

                        }
                        Spacer(modifier = Modifier.width(159.dp))
                    }
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        // offset in 1.5 times
                        modifier = Modifier
                            .width(42.dp)
                            .height(95.dp)
                            .align(Alignment.CenterEnd)
                            .offset(x = (-63).dp),
                        backgroundColor = MyColors.antiFlashWhite
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_heart),
                                    contentDescription = "favourite",
                                    tint = MyColors.ultraViolet
                                )
                            }

                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_line),
                                    contentDescription = "split line",
                                    tint = MyColors.ultraViolet
                                )
                            }

                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_google_sharing),
                                    contentDescription = "google sharing",
                                    tint = MyColors.ultraViolet
                                )
                            }
                        }
                    }

                }


                Spacer(modifier = Modifier.height(41.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(24.dp))
                    Box(
                        modifier = Modifier
                            .width(186.dp)
                            .height(86.dp)
                    ) {
                        Text(
                            text = name,
                            fontFamily = Fonts.poppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp
                        )

                    }

                }
            }

        }
    }
}