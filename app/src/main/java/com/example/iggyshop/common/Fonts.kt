package com.example.iggyshop.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.iggyshop.R

object Fonts {
    val montserratFamily = FontFamily(
        Font(resId = R.font.montserrat_medium, weight = FontWeight.Medium),
        Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold),
        Font(resId = R.font.montserrat_semibold, weight = FontWeight.SemiBold),
    )

    val poppinsFamily = FontFamily(
        fonts = listOf(
            Font(resId = R.font.poppins_regular, weight = FontWeight.Normal),
            Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),
            Font(resId = R.font.poppins_semibold, weight = FontWeight.SemiBold),
        )
    )
}