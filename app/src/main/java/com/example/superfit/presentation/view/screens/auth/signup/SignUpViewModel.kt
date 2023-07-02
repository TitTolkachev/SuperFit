package com.example.superfit.presentation.view.screens.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.model.RegisterRequestBody
import com.example.superfit.domain.usecase.local.SaveCredentialsToLocalStorageUseCase
import com.example.superfit.domain.usecase.remote.RegisterUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.view.model.ErrorType
import com.example.superfit.presentation.view.shared.errordialog.ErrorDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val saveCredentialsToLocalStorageUseCase: SaveCredentialsToLocalStorageUseCase
) : ViewModel() {

    var state by mutableStateOf(SignUpScreenState())
        private set

    var errorDialogState by mutableStateOf(ErrorDialogState())
        private set

    fun accept(event: SignUpScreenIntent) {
        when (event) {
            is SignUpScreenIntent.SignUp -> {

                val code = state.codeValue
                val repeatCode = state.repeatCodeValue

                if (code == repeatCode && code.length >= 4) {

                    viewModelScope.launch {
                        val request = registerUseCase.execute(
                            RegisterRequestBody(
                                event.emailValue,
                                code.toLong()
                            )
                        )

                        when (request) {
                            is Resource.Success -> {
                                saveCredentialsToLocalStorageUseCase.execute(
                                    Credentials(
                                        event.emailValue,
                                        code
                                    )
                                )
                                state = state.copy(showMainScreen = true)
                            }

                            is Resource.NetworkError -> {
                                errorDialogState = errorDialogState.copy(
                                    text = request.message,
                                    errorType = ErrorType.NETWORK
                                )
                            }

                            is Resource.ValidationError -> {
                                errorDialogState = errorDialogState.copy(
                                    text = request.message,
                                    errorType = ErrorType.VALIDATION
                                )
                            }

                            is Resource.Exception -> {
                                errorDialogState = errorDialogState.copy(
                                    text = request.message,
                                    errorType = ErrorType.UNEXPECTED
                                )
                            }
                        }
                    }
                }
            }

            is SignUpScreenIntent.NewUsernameText -> {
                state = state.copy(usernameValue = event.newText)
            }

            is SignUpScreenIntent.NewEmailText -> {
                state = state.copy(emailValue = event.newText)
            }

            is SignUpScreenIntent.NewPasswordText -> {
                state = state.copy(codeValue = event.newText)
            }

            is SignUpScreenIntent.NewRepeatPasswordText -> {
                state = state.copy(repeatCodeValue = event.newText)
            }

            is SignUpScreenIntent.NavigateToSignIn -> {
                state = state.copy(showSignInScreen = true)
            }

            is SignUpScreenIntent.ErrorDialogShowed -> {
                errorDialogState = errorDialogState.copy(text = null)
            }
        }
    }
}