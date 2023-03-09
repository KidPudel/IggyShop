package com.example.iggyshop.presentation.views

sealed class Screens(val route: String) {
    object SignUpScreen : Screens(route = "sign_up_screen")
    object LoginScreen : Screens(route = "login_screen")

    object PageOneScreen : Screens(route = "page_one_screen")
}
