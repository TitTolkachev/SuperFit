package com.example.superfit.presentation.view.screens.exercises.squats

import androidx.compose.runtime.Immutable

@Immutable
sealed class SquatsScreenIntent {
    object NavigateBack : SquatsScreenIntent()
    object Navigated : SquatsScreenIntent()
}

@Immutable
data class SquatsScreenState(
    val totalRepeatsCount: Int? = null,
    val repeatsCount: Int? = null,
    val navigateToSuccessScreen: Boolean? = null,
    val navigateBack: Boolean? = null
)