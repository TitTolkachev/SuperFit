package com.example.superfit.presentation.view.screens.exercises.running

import androidx.compose.runtime.Immutable


@Immutable
sealed class RunningScreenIntent {
    object Navigated : RunningScreenIntent()
    object Finish : RunningScreenIntent()
}

@Immutable
data class RunningScreenState(
    val totalRepeatsCount: Int? = null,
    val repeatsCount: Int? = null,
    val navigateToSuccessScreen: Boolean? = null,
    val navigateToUnSuccessScreen: Boolean? = null
)