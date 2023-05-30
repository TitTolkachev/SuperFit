package com.example.superfit.presentation.view.screens.main.body

import androidx.compose.runtime.Immutable

@Immutable
sealed class BodyScreenUiEvent {
    object EditWeight : BodyScreenUiEvent()
    object EditHeight : BodyScreenUiEvent()
    object ShowTrainProgress : BodyScreenUiEvent()
    object ShowStatistics : BodyScreenUiEvent()
    object ShowImages : BodyScreenUiEvent()
    object TakePicture : BodyScreenUiEvent()
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
)