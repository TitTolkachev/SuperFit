package com.example.superfit.presentation.view.screens.exercises.plank

import androidx.compose.runtime.Immutable

@Immutable
sealed class PlankScreenIntent {
    object Navigated : PlankScreenIntent()
    object Finish : PlankScreenIntent()
}

@Immutable
data class PlankScreenState(
    val totalRepeatsCount: Int? = null,
    val repeatsCount: Int? = null,
    val navigateToSuccessScreen: Boolean? = null,
    val navigateToUnSuccessScreen: Boolean? = null
)