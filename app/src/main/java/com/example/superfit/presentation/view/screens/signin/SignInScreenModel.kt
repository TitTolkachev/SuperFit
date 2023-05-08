package com.example.superfit.presentation.view.screens.signin

import androidx.compose.runtime.Immutable


@Immutable
sealed class SignInScreenUiEvent {
    data class SignIn(val emailValue: String, val codeValue: String, val repeatCodeValue: String) :
        SignInScreenUiEvent()
}

@Immutable
data class SignInScreenState(
    val emailValue: String,
    val codeValue: String,
    val repeatCodeValue: String,
    val isLoading: Boolean?,
    val showMainScreen: Boolean?
)