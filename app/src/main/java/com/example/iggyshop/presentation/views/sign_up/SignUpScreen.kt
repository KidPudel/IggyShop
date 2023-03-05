package com.example.iggyshop.presentation.views.sign_up

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.iggyshop.R
import com.example.iggyshop.common.Fonts.montserratFamily
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.presentation.views.CustomTextField

@Composable
fun SignUp(navigationController: NavController) {
    val firstNameState = remember { mutableStateOf(value = "") }
    val lastNameState = remember { mutableStateOf(value = "") }
    val emailState = remember { mutableStateOf(value = "") }
    val emailPlaceholder = remember {mutableStateOf(value = "Email")}
    var validState = remember {
        mutableStateOf(value = true)
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 43.dp)
    ) {
        Spacer(modifier = Modifier.height(151.dp))
        Text(
            text = "Sign up",
            fontWeight = FontWeight.SemiBold,
            fontFamily = montserratFamily,
            fontSize = 26.sp,
        )
        Spacer(modifier = Modifier.height(60.dp))

        CustomTextField(
            state = firstNameState,
            placeholder = "First name",
        )
        Spacer(modifier = Modifier.height(35.dp))

        CustomTextField(
            state = lastNameState,
            placeholder = "Last name",
        )
        Spacer(modifier = Modifier.height(35.dp))

        CustomTextField(
            state = emailState,
            placeholder = emailPlaceholder.value,
            onValidate = {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()) {
                    emailState.value = ""
                    emailPlaceholder.value = "Invalid email address"
                    validState.value = false
                } else {
                    emailPlaceholder.value = "Email"
                    validState.value  = true
                }
            },
            valid = validState.value
        )
        Spacer(modifier = Modifier.height(35.dp))

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
                fontFamily = montserratFamily,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Already have an account?",
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Log in",
                modifier = Modifier.clickable(onClick = {
                    navigationController.navigate("login_screen")
                }),
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Medium,
                color = MyColors.iris,
                fontSize = 10.sp
            )
        }
        Spacer(modifier = Modifier.height(76.dp))
        SignInWithService(
            text = "Sign in with Google",
            vectorResource = R.drawable.ic_google,
            iconDescription = "google icon"
        )

        Spacer(modifier = Modifier.height(44.dp))
        SignInWithService(
            text = "Sign in with Apple",
            vectorResource = R.drawable.ic_apple,
            iconDescription = "apple icon"
        )


    }
}

@Composable
fun SignInWithService(
    text: String,
    vectorResource: Int,
    iconDescription: String?
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.clickable { println(text) }) {
        Icon(
            imageVector = ImageVector.vectorResource(vectorResource),
            contentDescription = iconDescription,
            modifier = Modifier.offset(y = (-3).dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}