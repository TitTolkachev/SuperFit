package com.example.superfit.presentation.view.screens.main.image

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Photo

@Immutable
sealed class ImageScreenIntent {
    data class LoadImage(val imageId: String, val imageDate: String) : ImageScreenIntent()
    object NavigateBack : ImageScreenIntent()
    object Navigated : ImageScreenIntent()
}

@Immutable
data class ImageScreenState(
    val image: Photo? = null,
    val navigateBack: Boolean? = null
)