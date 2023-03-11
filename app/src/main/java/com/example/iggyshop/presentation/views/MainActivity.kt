package com.example.iggyshop.presentation.views


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iggyshop.R
import com.example.iggyshop.common.Fonts
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.presentation.views.login.LoginScreen
import com.example.iggyshop.presentation.views.page_one.PageOneScreen
import com.example.iggyshop.presentation.views.sign_up.SignUp
import com.example.iggyshop.ui.theme.IggyShopTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.assertThreadHoldsLock

@AndroidEntryPoint
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
                    NavHost(
                        navController = navigationController,
                        startDestination = Screens.PageOneScreen.route
                    ) {
                        composable(route = Screens.SignUpScreen.route) {
                            SignUp(navigationController = navigationController)
                        }
                        composable(route = Screens.LoginScreen.route) {
                            LoginScreen(navigationController = navigationController)
                        }
                        composable(route = Screens.PageOneScreen.route) {
                            PageOneScreen(navigationController = navigationController)
                        }
                        composable(route = Screens.ProfileScreen.route) {
                            ProfileScreen(navigationController = navigationController)
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
    onValidate: () -> Unit = {},
    valid: Boolean = true,
    vectorResource: Int? = null,
    fontFamily: FontFamily = Fonts.montserratFamily,
    fontWeight: FontWeight = FontWeight.Medium,
    fontSize: TextUnit = 11.sp,
    modifier: Modifier
) {
    val focusManager = LocalFocusManager.current
    BasicTextField(
        value = state.value,
        onValueChange = { entered: String -> state.value = entered },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize
        ),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(contentAlignment = Alignment.Center) {
                if (state.value.isEmpty() && valid) {
                    Text(
                        text = placeholder,
                        color = Color.Gray,
                        fontWeight = fontWeight,
                        fontSize = fontSize
                    )
                } else if (state.value.isEmpty() && !valid) {
                    Text(
                        text = placeholder,
                        color = Color.Red,
                        fontWeight = fontWeight,
                        fontSize = fontSize
                    )
                }
                if (vectorResource != null) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = vectorResource),
                        contentDescription = "inner image",
                        tint = MyColors.davysGray,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(horizontal = 15.dp)
                    )
                }

                innerTextField()
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onValidate()
        })
    )
}

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

@Composable
fun MyBottomBar(navigationController: NavController) {
    val currentDestination = navigationController.currentDestination
    val bottomBarItems = listOf(
        BottomBarNavItem.Home,
        BottomBarNavItem.Heart,
        BottomBarNavItem.ShoppyTrolley,
        BottomBarNavItem.Message,
        BottomBarNavItem.Profile,
    )
    BottomAppBar(backgroundColor = MyColors.ghostWhite) {
        BottomNavigation(backgroundColor = MyColors.ghostWhite) {
            bottomBarItems.forEach { item ->
                BottomNavigationItem(
                    selected = item.route == currentDestination?.route,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.icon),
                            contentDescription = "bottom bar icon",
                            tint = Color.Gray
                        )
                    },
                    onClick = {
                        navigationController.navigate(route = item.route)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IggyShopTheme {
    }
}