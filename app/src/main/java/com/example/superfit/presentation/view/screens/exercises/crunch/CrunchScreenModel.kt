package com.example.superfit.presentation.view.screens.exercises.crunch

import androidx.compose.runtime.Immutable

@Immutable
sealed class CrunchScreenIntent {
    object Finish : CrunchScreenIntent()
    object Navigated : CrunchScreenIntent()
}

@Immutable
data class CrunchScreenState(
    val repeatsCount: Int? = null,
    val navigateToSuccessScreen: Boolean? = null
)