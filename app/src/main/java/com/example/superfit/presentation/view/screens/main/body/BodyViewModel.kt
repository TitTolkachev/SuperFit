package com.example.superfit.presentation.view.screens.main.body

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BodyViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(BodyScreenState(1, 1))
        private set

    fun accept(event: BodyScreenIntent) {
        when (event) {
            BodyScreenIntent.Navigated -> {
                state = state.copy(
                    showImage = null,
                    showImages = null,
                    showStatistics = null,
                    showTrainProgress = null
                )
            }

            BodyScreenIntent.EditHeight -> {

            }

            BodyScreenIntent.EditWeight -> {

            }

            BodyScreenIntent.ShowImages -> {

            }

            BodyScreenIntent.ShowStatistics -> {

            }

            BodyScreenIntent.ShowTrainProgress -> {

            }

            BodyScreenIntent.TakePicture -> {

            }

            is BodyScreenIntent.ShowImage -> {
                state = state.copy(showImage = event.image)
            }
        }
    }
}