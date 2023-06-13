package com.example.superfit.presentation.view.screens.main.body

import androidx.compose.runtime.Immutable

@Immutable
sealed class BodyScreenIntent {
    object Navigated : BodyScreenIntent()
    object EditWeight : BodyScreenIntent()
    object EditHeight : BodyScreenIntent()
    object ShowTrainProgress : BodyScreenIntent()
    object ShowStatistics : BodyScreenIntent()
    object ShowImages : BodyScreenIntent()
    object TakePicture : BodyScreenIntent()
    data class ShowImage(val image: Int) : BodyScreenIntent()
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
    val showImage: Int? = null
)