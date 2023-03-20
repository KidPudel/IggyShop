package com.example.shop_presentation.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shop_presentation.presentation.common.Fonts
import com.example.shop_presentation.presentation.common.MyColors
import com.example.shop_presentation.presentation.views.BottomBarNavItem

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
    // to clear focus
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
    BottomNavigation(
        backgroundColor = MyColors.whiteSmoke,
        modifier = Modifier
            .graphicsLayer(
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                ),
                // force bottom bar to fill size (fillMax'Width' doesn't work)
                scaleX = 1.02f,
                scaleY = 1.02f,
                clip = true
            ),
    ) {
        bottomBarItems.forEach { item ->
            val itemBackgroundColor =
                if (item.route == currentDestination?.route)
                    MyColors.antiFlashWhite else MyColors.whiteSmoke


            BottomNavigationItem(
                selected = item.route == currentDestination?.route,
                icon = {
                    Card(
                        shape = RoundedCornerShape(35.dp),
                        backgroundColor = itemBackgroundColor,
                        modifier = Modifier.size(40.dp),
                        elevation = 0.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = item.icon),
                                contentDescription = "bottom bar icon",
                                tint = Color.Gray,
                                modifier = Modifier.width(15.dp)
                            )
                        }
                    }
                },
                onClick = {
                    navigationController.navigate(route = item.route)
                },
                selectedContentColor = MyColors.antiFlashWhite
            )
        }
    }
}