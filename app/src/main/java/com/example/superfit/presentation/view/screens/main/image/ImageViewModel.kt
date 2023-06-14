package com.example.superfit.presentation.view.screens.main.image

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.usecase.remote.DownloadPhotoUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.view.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val downloadPhotoUseCase: DownloadPhotoUseCase
) : ViewModel() {

    var state by mutableStateOf(ImageScreenState())
        private set

    fun accept(event: ImageScreenIntent) {
        when (event) {
            is ImageScreenIntent.LoadImage -> {
                viewModelScope.launch {
                    when (val request = downloadPhotoUseCase.execute(event.imageId)) {
                        is Resource.Success -> {
                            request.data?.let {
                                withContext(Dispatchers.Main) {
                                    state = state.copy(
                                        image = Photo(
                                            event.imageId,
                                            event.imageDate,
                                            it
                                        )
                                    )
                                }
                            }
                        }

                        else -> {}
                    }
                }
            }

            ImageScreenIntent.NavigateBack -> {
                state = state.copy(navigateBack = true)
            }

            ImageScreenIntent.Navigated -> {
                state = state.copy(navigateBack = null)
            }
        }
    }
}