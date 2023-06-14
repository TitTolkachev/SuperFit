package com.example.superfit.presentation.view.screens.main.imagelist

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.GalleryBlock
import com.example.superfit.presentation.view.model.Photo


@Immutable
sealed class ImageListScreenIntent {
    data class ShowImage(val image: Photo) : ImageListScreenIntent()
    object NavigateBack : ImageListScreenIntent()
    object Navigated : ImageListScreenIntent()
}

@Immutable
data class ImageListScreenState(
    val galleryBlocks: List<GalleryBlock> = listOf(),
    val showImage: Photo? = null,
    val navigateBack: Boolean? = null
)