package com.example.iggyshop.common

import com.example.iggyshop.domain.models.User

object Constants {
    const val BASE_URL = "https://run.mocky.io/v3/"
    var user: User? = User(
        firstName = "Unknown",
        lastName = "Unknown",
        email = "Unknown"
    )
}