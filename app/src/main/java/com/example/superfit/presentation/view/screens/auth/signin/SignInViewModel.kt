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

    init {
        state = state.copy(numbers = state.numbers.shuffled())
    }

    fun accept(event: SignInScreenUiEvent) {
        when (event) {
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

            is SignInScreenUiEvent.ButtonClicked -> {
                state = if (state.password.length >= PASSWORD_MAX_LENGTH - 1) {
                    state.copy(showMainScreen = true, password = "")
                } else {
                    state.copy(
                        numbers = state.numbers.shuffled(),
                        password = state.password + event.value.toString()
                    )
                }
            }
        }
    }

    companion object {
        private const val PASSWORD_MAX_LENGTH = 4
    }
}