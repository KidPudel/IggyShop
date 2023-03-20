package com.example.iggyshop


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shop_presentation.presentation.common.MyColors
import com.example.shop_presentation.presentation.ui.theme.IggyShopTheme
import com.example.shop_presentation.presentation.views.Screens
import com.example.shop_presentation.presentation.views.login.LoginScreen
import com.example.shop_presentation.presentation.views.page_one.PageOneScreen
import com.example.shop_presentation.presentation.views.page_two.PageTwoScreen
import com.example.shop_presentation.presentation.views.profile.ProfileScreen
import com.example.shop_presentation.presentation.views.sign_up.SignUp
import dagger.hilt.android.AndroidEntryPoint

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
                        startDestination = Screens.SignUpScreen.route
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
                        composable(
                            route = Screens.PageTwoScreen.route + "/{image}/{name}",
                            arguments = listOf(
                                // icon argument
                                navArgument(name = "image", builder = {
                                    type = NavType.StringType
                                    nullable = true
                                }
                                ),
                                // title argument
                                navArgument(name = "name", builder = {
                                    type = NavType.StringType
                                    nullable = true
                                })
                            )
                        ) { navBackStackEntry ->
                            /* when composable is added to the back stack,
                               provide it with arguments passed
                               (get it by the key specified in navArgument)
                            */
                            PageTwoScreen(
                                navigationController = navigationController,
                                image = navBackStackEntry.arguments?.getString("image") ?: "error",
                                name = navBackStackEntry.arguments?.getString("name") ?: "error"
                            )
                        }
                    }
                }
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