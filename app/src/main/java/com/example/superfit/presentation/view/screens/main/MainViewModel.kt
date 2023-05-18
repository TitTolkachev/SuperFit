package com.example.superfit.presentation.view.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(MainScreenState())
        private set

    init {
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
                state = state.copy(signOut = true)
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