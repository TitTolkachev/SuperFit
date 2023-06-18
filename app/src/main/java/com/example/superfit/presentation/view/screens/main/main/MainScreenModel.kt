package com.example.superfit.presentation.view.screens.main.main

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.model.Exercises

@Immutable
sealed class MainScreenIntent {
    data class ShowExerciseScreen(val exercise: Exercises) : MainScreenIntent()
    object Update : MainScreenIntent()
    object ShowAllExercisesScreen : MainScreenIntent()
    object ShowBodyScreen : MainScreenIntent()
    object SignOut : MainScreenIntent()
    object Navigated : MainScreenIntent()
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