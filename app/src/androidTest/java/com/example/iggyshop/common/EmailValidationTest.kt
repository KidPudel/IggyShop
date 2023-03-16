package com.example.iggyshop.common


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmailValidationTest {
    @Test
    fun ifEmailIsInvalidReturnFalse() {
        val emailValid = EmailValidation.isEmailValid("invalid format")
        assertThat(emailValid).isFalse()
    }
}