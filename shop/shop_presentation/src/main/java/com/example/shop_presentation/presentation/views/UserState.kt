package com.example.shop_presentation.presentation.views

import com.example.shop_domain.domain.models.User

data class UserState (
    val user: User? = null,
    val isLoading: Boolean = false
)