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
import com.example.superfit.domain.usecase.remote.GetHistoryUseCase
import com.example.superfit.domain.usecase.remote.GetTrainingUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.view.model.EXERCISES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    saveEntranceInfoUseCase: SaveEntranceInfoUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val saveCredentialsToLocalStorageUseCase: SaveCredentialsToLocalStorageUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val getTrainingUseCase: GetTrainingUseCase
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())
        private set

    init {
        saveEntranceInfoUseCase.execute(true)
    }

    fun accept(event: MainScreenIntent) {
        when (event) {
            is MainScreenIntent.Update -> {

                // Body Params
                viewModelScope.launch {
                    when (val bodyHistoryRequest = getHistoryUseCase.execute()) {
                        is Resource.Success -> {
                            val currentBodyParams =
                                bodyHistoryRequest.data?.maxByOrNull { it.date }
                            if (currentBodyParams != null) {
                                withContext(Dispatchers.Main) {
                                    state = state.copy(
                                        bodyWeight = currentBodyParams.weight,
                                        bodyHeight = currentBodyParams.height
                                    )
                                }
                            }
                        }

                        else -> {}
                    }
                    when (val trainingRequest = getTrainingUseCase.execute()) {
                        is Resource.Success -> {
                            val currentBodyParams =
                                trainingRequest.data?.maxByOrNull { it.date }
                            if (currentBodyParams != null) {
                                val exercises = trainingRequest.data
                                    .sortedByDescending { it.date }
                                    .distinctBy { ex -> ex.exercise }
                                    .take(2)
                                    .map {
                                        EXERCISES.first { ex ->
                                            ex.exercise.name == it.exercise
                                        }
                                    }
                                withContext(Dispatchers.Main) {
                                    state = state.copy(
                                        lastExercises = exercises
                                    )
                                }
                            }
                        }

                        else -> {}
                    }
                }
            }

            is MainScreenIntent.ShowExerciseScreen -> {
                state = state.copy(showExercise = event.exercise)
            }

            is MainScreenIntent.ShowAllExercisesScreen -> {
                state = state.copy(showAllExercises = true)
            }

            is MainScreenIntent.ShowBodyScreen -> {
                state = state.copy(showBodyScreen = true)
            }

            is MainScreenIntent.SignOut -> {
                viewModelScope.launch {
                    saveTokenToLocalStorageUseCase.execute(Token("", ""))
                    saveCredentialsToLocalStorageUseCase.execute(Credentials("", ""))
                    state = state.copy(signOut = true)
                }
            }

            is MainScreenIntent.Navigated -> {
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