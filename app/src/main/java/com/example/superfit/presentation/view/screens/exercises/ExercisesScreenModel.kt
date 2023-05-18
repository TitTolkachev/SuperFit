package com.example.superfit.presentation.view.screens.exercises

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.model.Exercises

@Immutable
sealed class ExercisesScreenUiEvent {
    data class ShowExerciseScreen(val exercise: Exercises) : ExercisesScreenUiEvent()
    object NavigateBack : ExercisesScreenUiEvent()
    object Navigated : ExercisesScreenUiEvent()
}

@Immutable
data class ExercisesScreenState(
    val exercises: List<Exercise> = listOf(),
    val showExercise: Exercises? = null,
    val navigateBack: Boolean? = null
)