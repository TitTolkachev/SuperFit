package com.example.superfit.presentation.view.screens.main.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.model.Token
import com.example.superfit.domain.usecase.local.SaveCredentialsToLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveEntranceInfoUseCase
import com.example.superfit.domain.usecase.local.SaveTokenToLocalStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    saveEntranceInfoUseCase: SaveEntranceInfoUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val saveCredentialsToLocalStorageUseCase: SaveCredentialsToLocalStorageUseCase
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())
        private set

    init {
        saveEntranceInfoUseCase.execute(true)

        //TODO()
        state = state.copy(
            bodyWeight = 120,
            bodyHeight = 200
        )
    }

    fun accept(event: MainScreenUiEvent) {
        when (event) {
            is MainScreenUiEvent.ShowExerciseScreen -> {
                state = state.copy(showExercise = event.exercise)
            }

            is MainScreenUiEvent.ShowAllExercisesScreen -> {
                state = state.copy(showAllExercises = true)
            }

            is MainScreenUiEvent.ShowBodyScreen -> {
                state = state.copy(showBodyScreen = true)
            }

            is MainScreenUiEvent.SignOut -> {
                viewModelScope.launch {
                    saveTokenToLocalStorageUseCase.execute(Token("", ""))
                    saveCredentialsToLocalStorageUseCase.execute(Credentials("", ""))
                    state = state.copy(signOut = true)
                }
            }

            is MainScreenUiEvent.Navigated -> {
                state = state.copy(
                    showExercise = null,
                    showAllExercises = null,
                    showBodyScreen = null,
                    signOut = null
                )
            }
        }
    }
}