package com.example.superfit.presentation.view.screens.main.imagelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.usecase.remote.DownloadPhotoUseCase
import com.example.superfit.domain.usecase.remote.GetPhotosUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.helper.GalleryBlocksHelper
import com.example.superfit.presentation.helper.PhotoDateMapper
import com.example.superfit.presentation.view.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val downloadPhotoUseCase: DownloadPhotoUseCase,
    getPhotosUseCase: GetPhotosUseCase,
) : ViewModel() {

    var state by mutableStateOf(ImageListScreenState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getPhotosUseCase.execute()) {
                is Resource.Success -> {
                    val photos = request.data

                    val downloadedPhotos = mutableListOf<Pair<Long, Photo>>()
                    photos?.forEach {
                        val photoRequest = downloadPhotoUseCase.execute(it.id)
                        if (photoRequest is Resource.Success) {
                            downloadedPhotos.add(
                                Pair(
                                    it.uploaded, Photo(
                                        it.id,
                                        PhotoDateMapper.mapUploadedDateToString(it.uploaded),
                                        photoRequest.data!!
                                    )
                                )
                            )
                        }
                    }

                    val galleryBlocks =
                        GalleryBlocksHelper.mapPhotosToGalleryBlocks(downloadedPhotos)
                    withContext(Dispatchers.Main) {
                        state = state.copy(galleryBlocks = galleryBlocks)
                    }
                }

                else -> {}
            }
        }
    }

    fun accept(event: ImageListScreenIntent) {
        state = when (event) {
            is ImageListScreenIntent.ShowImage -> {
                state.copy(showImage = event.image.copy())
            }

            ImageListScreenIntent.NavigateBack -> {
                state.copy(navigateBack = true)
            }

            ImageListScreenIntent.Navigated -> {
                state.copy(navigateBack = null)
            }
        }
    }
}