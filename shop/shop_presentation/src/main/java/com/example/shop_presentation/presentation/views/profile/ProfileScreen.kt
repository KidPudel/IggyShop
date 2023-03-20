package com.example.shop_presentation.presentation.views.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shop_presentation.R
import com.example.shop_presentation.presentation.common.Constants
import com.example.shop_presentation.presentation.common.Fonts
import com.example.shop_presentation.presentation.common.MyColors
import com.example.shop_presentation.presentation.views.MyBottomBar
import com.example.shop_presentation.presentation.views.Screens

@Composable
fun ProfileScreen(navigationController: NavController) {
    val avatars = listOf(
        R.drawable.avatar,
        R.drawable.avatar_man,
        R.drawable.avatar_girl_first,
        R.drawable.avatar_girl_second,
    )
    // var currentAvatarIndex = 0
    val currentAvatar = remember { Constants.currentAvatar }
    Scaffold(
        topBar = {
            ProfileTopBar()
        },
        bottomBar = {
            MyBottomBar(navigationController = navigationController)
        }) { scaffoldPadding ->
        Box(
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .matchParentSize()
            ) {
                Image(
                    painter = painterResource(id = currentAvatar.value),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .size(61.dp)
                        .clip(shape = RoundedCornerShape(35.dp))
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(35.dp))
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Change photo",
                    fontFamily = Fonts.montserratFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    fontSize = 8.sp,
                    modifier = Modifier.clickable {
                        // change avatar
                        currentAvatar.value =
                            avatars.elementAt(
                                if (avatars.indexOf(currentAvatar.value) == avatars.size - 1) {
                                    0
                                } else {
                                    avatars.indexOf(currentAvatar.value) + 1
                                }
                            )
                    }
                )
                Spacer(modifier = Modifier.height(17.dp))
                Text(
                    text = "${Constants.user?.firstName} ${Constants.user?.lastName}",
                    fontFamily = Fonts.montserratFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(36.dp))
                UploadBottom()
                Spacer(modifier = Modifier.height(14.dp))

                Choice(
                    title = "Trade store",
                    leftIcon = R.drawable.ic_credit_card,
                    rightIcon = R.drawable.ic_forward
                )
                Choice(
                    title = "Payment method",
                    leftIcon = R.drawable.ic_credit_card,
                    rightIcon = R.drawable.ic_forward
                )
                Choice(
                    title = "Balance",
                    leftIcon = R.drawable.ic_credit_card,
                    balance = 1593
                )
                Choice(
                    title = "Trade history",
                    leftIcon = R.drawable.ic_credit_card,
                    rightIcon = R.drawable.ic_forward
                )
                Choice(
                    title = "Restore Purchase",
                    leftIcon = R.drawable.ic_restore,
                    rightIcon = R.drawable.ic_forward
                )
                Choice(title = "Help", leftIcon = R.drawable.ic_help)
                Choice(
                    title = "Log out",
                    leftIcon = R.drawable.ic_log_out,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            navigationController.navigate(route = Screens.SignUpScreen.route)
                        })


            }

        }
    }
}

@Composable
private fun ProfileTopBar() {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.height(80.dp),
    ) {
        Spacer(Modifier.height(23.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 23.dp)
        ) {
            // right side
            Icon(
                modifier = Modifier.padding(bottom = 15.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_navigation_back),
                contentDescription = "navigation back",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(136.dp))
            // custom text builder (center)
            Text(
                modifier = Modifier.padding(bottom = 15.dp),
                text = "Profile",
                fontFamily = Fonts.montserratFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 15.sp
            )
        }
    }
}


@Composable
fun UploadBottom() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(290.dp)
            .height(40.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(40.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                contentDescription = "upload item",
                modifier = Modifier.offset(y = 2.dp)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "Upload item",
                fontFamily = Fonts.montserratFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun Choice(
    title: String,
    leftIcon: Int,
    rightIcon: Int? = null,
    balance: Int? = null,
    modifier: Modifier = Modifier.size(40.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 41.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(35.dp),
            backgroundColor = MyColors.antiFlashWhite,
            modifier = modifier
        ) {
            Box(contentAlignment = Alignment.Center) {

                Icon(
                    imageVector = ImageVector.vectorResource(id = leftIcon),
                    contentDescription = title,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = title,
            fontFamily = Fonts.montserratFamily,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 14.sp
        )
        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.weight(1f)) {
            if (rightIcon != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = rightIcon),
                    contentDescription = "left icon",
                )
            } else if (balance != null) {
                Text(
                    text = "$ $balance",
                    fontFamily = Fonts.montserratFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 14.sp,
                )
            }

        }
    }
    Spacer(modifier = Modifier.height(37.dp))
}