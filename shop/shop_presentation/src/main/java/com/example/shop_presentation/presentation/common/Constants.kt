package com.example.shop_presentation.presentation.common

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.shop_domain.domain.models.User
import com.example.shop_presentation.R

object Constants {
    // to share user info across the app
    var user: User? = User(
        firstName = "Unknown",
        lastName = "Unknown",
        email = "Unknown"
    )

    val currentAvatar = mutableStateOf(R.drawable.avatar)
}