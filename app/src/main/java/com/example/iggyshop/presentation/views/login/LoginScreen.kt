package com.example.iggyshop.presentation.views.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.iggyshop.R
import com.example.iggyshop.common.Fonts
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.presentation.view_models.LoginViewModel
import com.example.iggyshop.presentation.views.CustomTextField
import com.example.iggyshop.presentation.views.Screens
import com.example.iggyshop.presentation.views.isEmailValid
import com.example.iggyshop.presentation.views.validate
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navigationController: NavController) {
    val emailState = remember { mutableStateOf(value = "") }
    val passwordState = remember { mutableStateOf(value = "") }
    val emailPlaceholder = remember { mutableStateOf(value = "Email") }
    val validState = remember { mutableStateOf(value = true) }

    val loginViewModel = hiltViewModel<LoginViewModel>()

    val currentScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyColors.ghostWhite)
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
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

                CustomTextField(
                    state = emailState,
                    placeholder = emailPlaceholder.value,
                    onValidate = {
                        validate(
                            emailState = emailState,
                            placeholderState = emailPlaceholder,
                            validState = validState
                        )
                    },
                    valid = validState.value
                )
                Spacer(modifier = Modifier.height(35.dp))

                CustomTextField(
                    state = passwordState,
                    placeholder = "Password",
                    vectorResource = R.drawable.ic_eye_off
                )


                Spacer(modifier = Modifier.height(99.dp))

                Button(
                    onClick = {
                        // validate that email is valid
                        if (isEmailValid(emailState.value)) {
                            loginViewModel.getUser(email = emailState.value)
                            // check if user is loaded from DB (valid user)
                            if (loginViewModel.state.value.user != null) {
                                navigationController.navigate(route = Screens.PageOneScreen.route)
                            } else {
                                // show message that user is not registered
                                currentScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "user does not registered",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
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
    }
}