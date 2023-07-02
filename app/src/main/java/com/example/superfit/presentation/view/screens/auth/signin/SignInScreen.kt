package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.shared.errordialog.ErrorDialog

@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {

    val state = viewModel.state
    val errorDialogState = viewModel.errorDialogState

    LaunchedEffect(key1 = state.showMainScreen) {
        if (state.showMainScreen == true) {
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.SignIn.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = state.showSignUpScreen) {
        if (state.showSignUpScreen == true) {
            navController.navigate(Screen.SignUp.route) {
                popUpTo(Screen.SignIn.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = true){
        viewModel.accept(SignInScreenIntent.ErrorDialogShowed)
    }
    if (errorDialogState.text != null) {
        ErrorDialog(errorDialogState) { viewModel.accept(SignInScreenIntent.ErrorDialogShowed) }
    }

    SignInScreenContent(state) { event: SignInScreenIntent -> viewModel.accept(event) }
}

@Composable
fun SignInScreenContent(state: SignInScreenState, sendEvent: (SignInScreenIntent) -> Unit) {

    if (state.currentPage == 1) {
        SignInFirstPage(state = state, sendEvent = sendEvent)
    } else if (state.currentPage == 2) {
        SignInSecondPage(state = state, sendEvent = sendEvent)
    }
}