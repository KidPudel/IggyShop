package com.example.iggyshop.presentation.views.sign_up

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.iggyshop.R
import com.example.iggyshop.common.Constants
import com.example.iggyshop.common.Fonts
import com.example.iggyshop.common.Fonts.montserratFamily
import com.example.iggyshop.common.MyColors
import com.example.iggyshop.domain.models.User
import com.example.iggyshop.presentation.view_models.SignUpViewModel
import com.example.iggyshop.presentation.views.CustomTextField
import com.example.iggyshop.presentation.views.Screens
import com.example.iggyshop.presentation.views.isEmailValid
import com.example.iggyshop.presentation.views.validate
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun SignUp(navigationController: NavController) {
    val firstNameState = remember { mutableStateOf(value = "") }
    val lastNameState = remember { mutableStateOf(value = "") }
    val emailState = remember { mutableStateOf(value = "") }
    val emailPlaceholder = remember { mutableStateOf(value = "Email") }
    val validState = remember {
        mutableStateOf(value = true)
    }

    val currentScope = rememberCoroutineScope()

    val signUpViewModel = hiltViewModel<SignUpViewModel>()

    // gets access to scaffold components
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyColors.ghostWhite)
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(scaffoldPadding)) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 43.dp)
            ) {
                Spacer(modifier = Modifier.height(121.dp))
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(29.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(35.dp))
                )
                Spacer(modifier = Modifier.height(35.dp))

                CustomTextField(
                    state = lastNameState,
                    placeholder = "Last name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(29.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(35.dp))
                )
                Spacer(modifier = Modifier.height(35.dp))

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
                    valid = validState.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(29.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(35.dp))
                )
                Spacer(modifier = Modifier.height(35.dp))

                Button(
                    onClick = {
                        // validate that all fields are filled correctly
                        if (firstNameState.value.isNotEmpty() &&
                            lastNameState.value.isNotEmpty() &&
                            isEmailValid(emailState.value)
                        ) {
                            // check if user is in database (block to prevent not complete response)
                            runBlocking {
                                signUpViewModel.getUser(emailState.value)
                            }
                            if (signUpViewModel.state.value.user != null) {
                                currentScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "user already exists",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            } else {
                                // create a new user
                                Constants.user = User(
                                    firstName = firstNameState.value,
                                    lastName = lastNameState.value,
                                    email = emailState.value
                                )
                                // add them to a database
                                signUpViewModel.addUser(Constants.user!!)
                                currentScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "signed in successfully",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                // go to the app
                                navigationController.navigate(route = Screens.PageOneScreen.route)
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
                            navigationController.navigate(Screens.LoginScreen.route)
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
    }
}

@Composable
private fun SignInWithService(
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




// TODO: abstract code on sign up actions