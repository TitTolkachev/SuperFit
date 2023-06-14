package com.example.superfit.presentation.view.screens.main.body

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.Photo

@Immutable
sealed class BodyScreenIntent {
    object Navigated : BodyScreenIntent()
    object EditWeight : BodyScreenIntent()
    object EditHeight : BodyScreenIntent()
    object ShowTrainProgress : BodyScreenIntent()
    object ShowStatistics : BodyScreenIntent()
    object ShowImages : BodyScreenIntent()
    object TakePicture : BodyScreenIntent()
    object CloseDialog : BodyScreenIntent()
    data class SaveImage(val image: Bitmap) : BodyScreenIntent()
    data class ShowImage(val image: Photo) : BodyScreenIntent()
    data class ImageSelected(val image: Uri?) : BodyScreenIntent()
}

@Immutable
data class BodyScreenState(
    val weight: Int,
    val height: Int,
    val editWeight: Boolean? = null,
    val editHeight: Boolean? = null,
    val showTrainProgress: Boolean? = null,
    val showStatistics: Boolean? = null,
    val showImages: Boolean? = null,
    val showImage: Photo? = null,
    val takePicture: Boolean? = null,
    val imageUri: Uri? = null,
    val firstPhoto: Photo? = null,
    val lastPhoto: Photo? = null
)