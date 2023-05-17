package com.example.superfit.presentation.view.screens.main

import androidx.compose.runtime.Immutable

@Immutable
sealed class MainScreenUiEvent {

    data class ShowExerciseScreen(val exerciseIndex: Int) : MainScreenUiEvent()
    object ShowAllExercisesScreen : MainScreenUiEvent()
    object ShowBodyScreen : MainScreenUiEvent()
    object SignOut : MainScreenUiEvent()
    object Navigated : MainScreenUiEvent()
}

@Immutable
data class MainScreenState(
    val bodyWeight: Int? = null,
    val bodyHeight: Int? = null,
    val lastExercises: List<Int>? = null,
    val showExercise: Int? = null,
    val showAllExercises: Boolean? = null,
    val showBodyScreen: Boolean? = null,
    val signOut: Boolean? = null,
)