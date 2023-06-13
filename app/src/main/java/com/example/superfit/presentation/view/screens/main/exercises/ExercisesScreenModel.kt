package com.example.superfit.presentation.view.screens.main.exercises

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.model.Exercises

@Immutable
sealed class ExercisesScreenIntent {
    data class ShowExerciseScreen(val exercise: Exercises) : ExercisesScreenIntent()
    object NavigateBack : ExercisesScreenIntent()
    object Navigated : ExercisesScreenIntent()
}

@Immutable
data class ExercisesScreenState(
    val exercises: List<Exercise> = listOf(),
    val showExercise: Exercises? = null,
    val navigateBack: Boolean? = null
)