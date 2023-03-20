package com.example.common.common

import androidx.compose.runtime.MutableState

object EmailValidation {
    /**
     * check if email address is valid, by scanning it with regex
     */
    fun isEmailValid(email: String): Boolean {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        return false
    }

    /**
     * validate that the address is valid and set corresponding states
     * I would validate on the server and every click, so user would make lesser steps
     * (+ validate by password is better)
     */
    fun validate(
        emailState: MutableState<String>,
        validState: MutableState<Boolean>,
        placeholderState: MutableState<String>
    ) {
        if (isEmailValid(emailState.value)) {
            validState.value = true
            placeholderState.value = "Email"
        } else {
            validState.value = false
            emailState.value = ""
            placeholderState.value = "Invalid email address"
        }
    }
}