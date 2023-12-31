package com.example.superfit.presentation.view.screens.auth.signup

import androidx.compose.runtime.Immutable

@Immutable
sealed class SignUpScreenIntent {
    data class SignUp(val emailValue: String, val codeValue: String, val repeatCodeValue: String) :
        SignUpScreenIntent()

    data class NewUsernameText(val newText: String) : SignUpScreenIntent()
    data class NewEmailText(val newText: String) : SignUpScreenIntent()
    data class NewPasswordText(val newText: String) : SignUpScreenIntent()
    data class NewRepeatPasswordText(val newText: String) : SignUpScreenIntent()
    object NavigateToSignIn : SignUpScreenIntent()
    object ErrorDialogShowed : SignUpScreenIntent()
}

@Immutable
data class SignUpScreenState(
    val usernameValue: String = "",
    val emailValue: String = "",
    val codeValue: String = "",
    val repeatCodeValue: String = "",
    val showMainScreen: Boolean? = null,
    val showSignInScreen: Boolean? = null
)