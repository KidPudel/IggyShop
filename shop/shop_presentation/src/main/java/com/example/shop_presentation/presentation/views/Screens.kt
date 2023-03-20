package com.example.shop_presentation.presentation.views

sealed class Screens(
    val route: String
) {
    object SignUpScreen : Screens(route = "sign_up_screen")
    object LoginScreen : Screens(route = "login_screen")
    object PageOneScreen : Screens(route = "page_one_screen")

    object ProfileScreen : Screens(route = "profile_screen")

    object PageTwoScreen : Screens(route = "page_two_screen")
}
