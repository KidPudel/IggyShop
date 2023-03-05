package com.example.iggyshop.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iggyshop.common.Fonts
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.presentation.views.login.LoginScreen
import com.example.iggyshop.presentation.views.sign_up.SignUp
import com.example.iggyshop.ui.theme.IggyShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IggyShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MyColors.ghostWhite,

                ) {
                    // create a navigator host and controller for it
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Screens.SignUpScreen.route) {
                        composable(route = Screens.SignUpScreen.route) {
                            SignUp(navigationController = navigationController)
                        }
                        composable(Screens.LoginScreen.route) {
                            LoginScreen(navigationController = navigationController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    state: MutableState<String>,
    placeholder: String,
) {
    BasicTextField(
        value = state.value,
        onValueChange = { entered: String -> state.value = entered },
        modifier = Modifier
            .fillMaxWidth()
            .height(29.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(35.dp)),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontFamily = Fonts.montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp
        ),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box (contentAlignment = Alignment.Center) {
                if (state.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color.Gray,
                        fontFamily = Fonts.montserratFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                    )
                }
                innerTextField()
            }

        }
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IggyShopTheme {
    }
}