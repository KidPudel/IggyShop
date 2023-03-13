package com.example.iggyshop.common

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.iggyshop.R
import com.example.iggyshop.domain.models.User

object Constants {
    const val BASE_URL = "https://run.mocky.io/v3/"

    // to share user info across the app
    var user: User? = User(
        firstName = "Unknown",
        lastName = "Unknown",
        email = "Unknown"
    )

    val currentAvatar = mutableStateOf(R.drawable.avatar)
}