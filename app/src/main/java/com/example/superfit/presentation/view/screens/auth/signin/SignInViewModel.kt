package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(SignInScreenState())
        private set

    fun accept(event: SignInScreenUiEvent) {
        when (event) {
            is SignInScreenUiEvent.SignIn -> {

                // TODO()
                state = state.copy(showMainScreen = true)
            }

            is SignInScreenUiEvent.SignUp -> {

                state = state.copy(showSignUpScreen = true)
            }

            is SignInScreenUiEvent.NextPage -> {
                state = state.copy(currentPage = 2)
            }

            is SignInScreenUiEvent.PrevPage -> {
                state = state.copy(currentPage = 1)
            }

            is SignInScreenUiEvent.NewUserNameText -> {
                state = state.copy(userName = event.newText)
            }
        }
    }
}