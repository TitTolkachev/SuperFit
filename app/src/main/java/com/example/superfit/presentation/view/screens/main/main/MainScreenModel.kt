package com.example.superfit.presentation.view.screens.main.main

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.model.Exercises

@Immutable
sealed class MainScreenUiEvent {
    data class ShowExerciseScreen(val exercise: Exercises) : MainScreenUiEvent()
    object ShowAllExercisesScreen : MainScreenUiEvent()
    object ShowBodyScreen : MainScreenUiEvent()
    object SignOut : MainScreenUiEvent()
    object Navigated : MainScreenUiEvent()
}

@Immutable
data class MainScreenState(
    val bodyWeight: Int? = null,
    val bodyHeight: Int? = null,
    val lastExercises: List<Exercise>? = null,
    val showExercise: Exercises? = null,
    val showAllExercises: Boolean? = null,
    val showBodyScreen: Boolean? = null,
    val signOut: Boolean? = null,
)