@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.superfit.presentation.view.screens.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.core.util.TestTags
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.shared.auth.AuthBrandText
import com.example.superfit.presentation.view.shared.auth.AuthEditText
import com.example.superfit.presentation.view.shared.errordialog.ErrorDialog

@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {

    val state = viewModel.state
    val errorDialogState = viewModel.errorDialogState

    LaunchedEffect(key1 = state.showMainScreen) {
        if (state.showMainScreen == true) {
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.SignUp.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = state.showSignInScreen) {
        if (state.showSignInScreen == true) {
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.SignUp.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = true){
        viewModel.accept(SignUpScreenIntent.ErrorDialogShowed)
    }
    if (errorDialogState.text != null) {
        ErrorDialog(errorDialogState) {viewModel.accept(SignUpScreenIntent.ErrorDialogShowed)}
    }

    SingUpScreenContent(state) { event: SignUpScreenIntent -> viewModel.accept(event) }
}

@ExperimentalMaterial3Api
@Composable
fun SingUpScreenContent(
    state: SignUpScreenState,
    sendEvent: (SignUpScreenIntent) -> Unit
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

        AuthBrandText(padding = PaddingValues(top = 68.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthEditText(
                isEmail = true,
                placeholderText = stringResource(R.string.auth_user_name),
                text = { state.usernameValue }) { text ->
                sendEvent(SignUpScreenIntent.NewUsernameText(text))
            }

            AuthEditText(
                isEmail = true,
                placeholderText = stringResource(R.string.auth_email),
                text = { state.emailValue }) { text ->
                sendEvent(SignUpScreenIntent.NewEmailText(text))
            }

            AuthEditText(
                isPassword = true,
                placeholderText = stringResource(R.string.auth_code),
                text = { state.codeValue }) { text ->
                sendEvent(SignUpScreenIntent.NewPasswordText(text))
            }

            AuthEditText(
                isPassword = true,
                placeholderText = stringResource(R.string.auth_repeat_code),
                text = { state.repeatCodeValue }) { text ->
                sendEvent(SignUpScreenIntent.NewRepeatPasswordText(text))
            }

            SignUpButton {
                sendEvent(
                    SignUpScreenIntent.SignUp(
                        state.emailValue,
                        state.codeValue,
                        state.repeatCodeValue
                    )
                )
            }
        }

        ToSignInButton { sendEvent(SignUpScreenIntent.NavigateToSignIn) }
    }
}

@Composable
private fun ToSignInButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.padding(bottom = 26.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = R.drawable.white_left_arrow),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 4.dp)
        )
        Text(
            text = stringResource(R.string.auth_sign_in),
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
        modifier = Modifier
            .offset(x = 5.dp)
            .testTag(TestTags.SIGN_UP_BUTTON),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Text(
            text = stringResource(R.string.auth_sign_up),
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