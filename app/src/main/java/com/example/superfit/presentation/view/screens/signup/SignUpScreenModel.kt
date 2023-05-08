package com.example.superfit.presentation.view.screens.signup

import androidx.compose.runtime.Immutable

@Immutable
sealed class SignUpScreenUiEvent {
    data class SignUp(val emailValue: String, val codeValue: String, val repeatCodeValue: String) :
        SignUpScreenUiEvent()

    data class NewEmailText(val newText: String) : SignUpScreenUiEvent()

    data class NewPasswordText(val newText: String) : SignUpScreenUiEvent()

    data class NewRepeatPasswordText(val newText: String) : SignUpScreenUiEvent()
}

@Immutable
data class SignUpScreenState(
    val emailValue: String = "",
    val codeValue: String = "",
    val repeatCodeValue: String = "",
    val isLoading: Boolean? = null,
    val showMainScreen: Boolean? = null,
    val showSignInScreen: Boolean? = null
)