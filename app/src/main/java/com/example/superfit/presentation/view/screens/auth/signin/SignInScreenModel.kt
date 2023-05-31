package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.Immutable

@Immutable
sealed class SignInScreenUiEvent {
    object NextPage : SignInScreenUiEvent()
    object PrevPage : SignInScreenUiEvent()
    object SignUp : SignInScreenUiEvent()
    data class ButtonClicked(val value: Int) : SignInScreenUiEvent()
    data class NewUserNameText(val newText: String) : SignInScreenUiEvent()
}

@Immutable
data class SignInScreenState(
    val userName: String = "",
    val currentPage: Int = 1,
    val isLoading: Boolean? = null,
    val showMainScreen: Boolean? = null,
    val showSignUpScreen: Boolean? = null,

    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
    val password: String = ""
)