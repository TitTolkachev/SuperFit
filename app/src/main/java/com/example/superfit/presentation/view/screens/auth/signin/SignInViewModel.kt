package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.usecase.local.SaveCredentialsToLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveEntranceInfoUseCase
import com.example.superfit.domain.usecase.remote.RefreshRefreshTokenUseCase
import com.example.superfit.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val refreshRefreshTokenUseCase: RefreshRefreshTokenUseCase,
    private val saveEntranceInfoUseCase: SaveEntranceInfoUseCase,
    private val saveCredentialsToLocalStorageUseCase: SaveCredentialsToLocalStorageUseCase
) : ViewModel() {

    var state by mutableStateOf(SignInScreenState())
        private set

    init {
        state = state.copy(numbers = state.numbers.shuffled())
    }

    fun accept(event: SignInScreenIntent) {
        when (event) {
            is SignInScreenIntent.SignUp -> {

                state = state.copy(showSignUpScreen = true)
            }

            is SignInScreenIntent.NextPage -> {
                state = state.copy(currentPage = 2)
            }

            is SignInScreenIntent.PrevPage -> {
                state = state.copy(currentPage = 1)
            }

            is SignInScreenIntent.NewUserNameText -> {
                state = state.copy(userName = event.newText)
            }

            is SignInScreenIntent.ButtonClicked -> {
                if (state.password.length >= PASSWORD_MAX_LENGTH - 1) {
                    viewModelScope.launch {
                        state = if (login(state.userName, state.password + event.value.toString()))
                            state.copy(
                                showMainScreen = true,
                                password = ""
                            )
                        else
                            state.copy(numbers = state.numbers.shuffled(), password = "")
                    }
                } else {
                    state = state.copy(
                        numbers = state.numbers.shuffled(),
                        password = state.password + event.value.toString()
                    )
                }
            }
        }
    }

    private suspend fun login(userName: String, password: String): Boolean {
        val request = refreshRefreshTokenUseCase.execute(
            LoginRequestBody(
                login = userName,
                password = password
            )
        )

        if (request is Resource.Success) {
            saveCredentialsToLocalStorageUseCase.execute(Credentials(userName, password))
            saveEntranceInfoUseCase.execute(true)
            return true
        }
        return false
    }

    companion object {
        private const val PASSWORD_MAX_LENGTH = 4
    }
}