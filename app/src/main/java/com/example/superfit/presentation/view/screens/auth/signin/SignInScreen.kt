package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.navigation.Screen

@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {

    val state = viewModel.state

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

    SignInScreenContent(state) { event: SignInScreenUiEvent -> viewModel.accept(event) }
}

@Composable
fun SignInScreenContent(state: SignInScreenState, sendEvent: (SignInScreenUiEvent) -> Unit) {

    if (state.currentPage == 1) {
        SignInFirstPage(state = state, sendEvent = sendEvent)
    } else if (state.currentPage == 2) {
        SignInSecondPage(state = state, sendEvent = sendEvent)
    }
}