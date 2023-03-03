package com.example.iggyshop.presentation.views.sign_up

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage

@Composable
fun SignUp() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.Gray)
    ) {
        Card(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "https://static.wikia.nocookie.net/smiling-friends/images/e/e5/Glep1.png/revision/latest?cb=20221016232719",
                contentDescription = "glep"
            )
        }
    }
}