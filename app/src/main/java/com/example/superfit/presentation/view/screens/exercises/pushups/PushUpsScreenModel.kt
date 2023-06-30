package com.example.superfit.presentation.view.screens.exercises.pushups

import androidx.compose.runtime.Immutable

@Immutable
sealed class PushUpsScreenIntent {
    object Navigated : PushUpsScreenIntent()
    object Finish : PushUpsScreenIntent()
}

@Immutable
data class PushUpsScreenState(
    val totalRepeatsCount: Int? = null,
    val repeatsCount: Int? = null,
    val navigateToSuccessScreen: Boolean? = null,
    val navigateToUnSuccessScreen: Boolean? = null
)