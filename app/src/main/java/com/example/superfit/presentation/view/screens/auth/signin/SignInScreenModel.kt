package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.Immutable

@Immutable
sealed class SignInScreenUiEvent {
    object SignIn : SignInScreenUiEvent()
    object NextPage : SignInScreenUiEvent()
    object PrevPage : SignInScreenUiEvent()
    object SignUp : SignInScreenUiEvent()
    data class NewUserNameText(val newText: String) : SignInScreenUiEvent()
}

@Immutable
data class SignInScreenState(
    val userName: String = "",
    val currentPage: Int = 1,
    val isLoading: Boolean? = null,
    val showMainScreen: Boolean? = null,
    val showSignUpScreen: Boolean? = null
)