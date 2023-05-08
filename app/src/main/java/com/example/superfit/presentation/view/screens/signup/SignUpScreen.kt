@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.superfit.presentation.view.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.shared.AuthBrandText
import com.example.superfit.presentation.view.shared.AuthEditText

@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {

    val state = viewModel.state

    LaunchedEffect(key1 = state.showMainScreen) {
        if (state.showMainScreen == true) {
            // TODO()
            navController.popBackStack()
            navController.navigate(Screen.SignIn.route)
        }
    }

    SingUpScreenContent(state) { event: SignUpScreenUiEvent -> viewModel.accept(event) }
}

@ExperimentalMaterial3Api
@Composable
fun SingUpScreenContent(
    state: SignUpScreenState,
    sendEvent: (SignUpScreenUiEvent) -> Unit
) {

    Image(
        painter = painterResource(id = R.drawable.auth_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        AuthBrandText(padding = PaddingValues(start = 38.dp, end = 38.dp, top = 68.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthEditText(
                isEmail = true,
                placeholderText = "Email",
                text = { state.emailValue }) { text ->
                sendEvent(SignUpScreenUiEvent.NewEmailText(text))
            }

            AuthEditText(
                isPassword = true,
                placeholderText = "Code",
                text = { state.codeValue }) { text ->
                sendEvent(SignUpScreenUiEvent.NewPasswordText(text))
            }

            AuthEditText(
                isPassword = true,
                placeholderText = "RepeatCode",
                text = { state.repeatCodeValue }) { text ->
                sendEvent(SignUpScreenUiEvent.NewRepeatPasswordText(text))
            }

            SignUpButton {
                sendEvent(
                    SignUpScreenUiEvent.SignUp(
                        state.emailValue,
                        state.codeValue,
                        state.repeatCodeValue
                    )
                )
            }
        }

        ToSignInButton {}
    }
}

@Composable
private fun ToSignInButton(
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = R.drawable.white_left_arrow),
            contentDescription = null
        )
        Text(
            text = "Sign In",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Text(
            text = "Sign Up",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.white_right_arrow),
            contentDescription = null
        )
    }
}