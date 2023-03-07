package com.example.iggyshop.domain.models

import com.example.iggyshop.data.database.model.UserDBModel

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
)

// map User model to UserDBModel
fun User.toUserDBModel(): UserDBModel {
    return UserDBModel(
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}
