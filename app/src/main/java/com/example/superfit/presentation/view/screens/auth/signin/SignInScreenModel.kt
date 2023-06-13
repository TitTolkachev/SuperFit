package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.Immutable

@Immutable
sealed class SignInScreenIntent {
    object NextPage : SignInScreenIntent()
    object PrevPage : SignInScreenIntent()
    object SignUp : SignInScreenIntent()
    data class ButtonClicked(val value: Int) : SignInScreenIntent()
    data class NewUserNameText(val newText: String) : SignInScreenIntent()
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