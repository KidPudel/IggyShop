package com.example.iggyshop.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navigationController: NavController) {
    Scaffold(bottomBar = {
        MyBottomBar(navigationController = navigationController)
    }) {scaffoldPadding ->
        Box(modifier = Modifier.padding(scaffoldPadding)) {
            Column {
                Text(text = "profile")
            }
            
        }
    }
}