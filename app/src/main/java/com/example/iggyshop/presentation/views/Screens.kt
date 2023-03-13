package com.example.iggyshop.presentation.views

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.iggyshop.R

sealed class Screens(
    val route: String
) {
    object SignUpScreen : Screens(route = "sign_up_screen")
    object LoginScreen : Screens(route = "login_screen")
    object PageOneScreen : Screens(route = "page_one_screen")

    object ProfileScreen : Screens(route = "profile_screen")

    object PageTwoScreen : Screens(route = "page_two_screen")
}
