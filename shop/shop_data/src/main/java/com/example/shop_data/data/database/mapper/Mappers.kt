package com.example.shop_data.data.database.mapper

import com.example.shop_data.data.database.model.UserDBModel
import com.example.shop_domain.domain.models.User

fun User.toUserDBModel(): UserDBModel {
    return UserDBModel(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}