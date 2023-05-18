package com.example.superfit.presentation.view.screens.exercises

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Exercise

@Immutable
sealed class ExercisesScreenUiEvent {
    data class ShowExerciseScreen(val exerciseIndex: Int) : ExercisesScreenUiEvent()
    object NavigateBack : ExercisesScreenUiEvent()
    object Navigated : ExercisesScreenUiEvent()
}

@Immutable
data class ExercisesScreenState(
    val exercises: List<Exercise> = listOf(),
    val showExercise: Int? = null,
    val navigateBack: Boolean? = null
)