package com.example.superfit.presentation.view.screens.main.image

import androidx.compose.runtime.Immutable

@Immutable
sealed class ImageScreenIntent {

}

@Immutable
data class ImageScreenState(
    val image: String? = null
)