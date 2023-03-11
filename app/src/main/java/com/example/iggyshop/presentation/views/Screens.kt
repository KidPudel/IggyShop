package com.example.iggyshop.presentation.views

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.iggyshop.R

sealed class Screens(
    val route: String,
    val vectorResource: Int? = null
) {
    object SignUpScreen : Screens(route = "sign_up_screen")
    object LoginScreen : Screens(route = "login_screen")
    object PageOneScreen : Screens(
        route = "page_one_screen",
        vectorResource = R.drawable.ic_home
    )
    object ProfileScreen : Screens(
        route = "profile_screen",
        vectorResource = R.drawable.ic_profile
    )
}
