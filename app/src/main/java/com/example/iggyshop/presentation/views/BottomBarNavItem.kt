package com.example.iggyshop.presentation.views

import com.example.iggyshop.R

sealed class BottomBarNavItem(val route: String, val icon: Int) {
    object Home : BottomBarNavItem(route = "page_one_screen", icon = R.drawable.ic_home)
    object Heart : BottomBarNavItem(route = "heart_screen", icon = R.drawable.ic_heart)
    object ShoppyTrolley : BottomBarNavItem(route = "shoppy_trolley_screen", icon = R.drawable.ic_shoppy_trolley)
    object Message : BottomBarNavItem(route = "message_screen", icon = R.drawable.ic_message)
    object Profile : BottomBarNavItem(route = "profile_screen", icon = R.drawable.ic_profile)
}
