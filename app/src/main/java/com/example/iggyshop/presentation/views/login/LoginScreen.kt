package com.example.iggyshop.presentation.views.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iggyshop.common.Fonts
import com.example.iggyshop.presentation.views.CustomTextField

@Composable
fun LoginScreen(navigationController: NavController) {
    val firstNameState = remember { mutableStateOf(value = "") }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 43.dp)
    ) {
        Spacer(modifier = Modifier.height(151.dp))
        Text(
            text = "Welcome back",
            fontWeight = FontWeight.SemiBold,
            fontFamily = Fonts.montserratFamily,
            fontSize = 26.sp,
        )
        Spacer(modifier = Modifier.height(60.dp))

        CustomTextField(firstNameState, "First name")
        Spacer(modifier = Modifier.height(35.dp))

        CustomTextField(firstNameState, "Password")


        Spacer(modifier = Modifier.height(99.dp))

        Button(
            onClick = {

            }, modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign up",
                fontWeight = FontWeight.Bold,
                fontFamily = Fonts.montserratFamily,
                fontSize = 14.sp
            )
        }
    }
}