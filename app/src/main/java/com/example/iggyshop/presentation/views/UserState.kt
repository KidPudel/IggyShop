package com.example.iggyshop.presentation.views

import com.example.iggyshop.domain.models.User

data class UserState (
    val user: User? = null,
    val isLoading: Boolean = false
)