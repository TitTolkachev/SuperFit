package com.example.superfit.presentation.view.screens.main.progress

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.ExerciseProgress


@Immutable
sealed class ProgressScreenIntent {
    object NavigateBack : ProgressScreenIntent()
    object Navigated : ProgressScreenIntent()
}

@Immutable
data class ProgressScreenState(
    val exercises: List<ExerciseProgress> = listOf(),
    val navigateBack: Boolean? = null
)