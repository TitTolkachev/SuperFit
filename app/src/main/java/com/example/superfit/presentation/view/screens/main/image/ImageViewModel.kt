package com.example.superfit.presentation.view.screens.main.image

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(ImageScreenState())
        private set

    fun accept(event: ImageScreenIntent) {
//        when (event) {
//
//        }
    }
}