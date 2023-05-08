package com.example.superfit.presentation.view.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(SignUpScreenState("", "", "", null, null))
        private set

    fun accept(event: SignUpScreenUiEvent) {
        when (event) {
            is SignUpScreenUiEvent.SignUp -> {
                state = state.copy(isLoading = true)

                // TODO()

                state = state.copy(isLoading = false, showMainScreen = true)
            }

            is SignUpScreenUiEvent.NewEmailText -> {
                state = state.copy(emailValue = event.newText)
            }

            is SignUpScreenUiEvent.NewPasswordText -> {
                state = state.copy(codeValue = event.newText)
            }

            is SignUpScreenUiEvent.NewRepeatPasswordText -> {
                state = state.copy(repeatCodeValue = event.newText)
            }
        }
    }
}