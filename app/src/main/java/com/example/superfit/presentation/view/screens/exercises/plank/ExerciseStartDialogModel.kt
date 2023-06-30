package com.example.superfit.presentation.view.screens.exercises.plank

import androidx.compose.runtime.Immutable


@Immutable
sealed class ExerciseStartDialogIntent {
    object Cancel : ExerciseStartDialogIntent()
    object Start : ExerciseStartDialogIntent()
}

@Immutable
data class ExerciseStartDialogState(
    val repeats: Int? = null,
    val isActive: Boolean = false,
    val exit: Boolean? = null
)