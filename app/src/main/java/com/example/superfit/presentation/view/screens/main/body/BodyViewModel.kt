package com.example.superfit.presentation.view.screens.main.body

import android.provider.MediaStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.usecase.remote.DownloadPhotoUseCase
import com.example.superfit.domain.usecase.remote.UploadImageUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.helper.PhotoDateMapper
import com.example.superfit.presentation.view.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BodyViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val downloadPhotoUseCase: DownloadPhotoUseCase
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
                state = state.copy(takePicture = true)
            }

            is BodyScreenIntent.ShowImage -> {
                state = state.copy(showImage = event.image)
            }

            BodyScreenIntent.CloseDialog -> {
                // TODO
                state = state.copy(takePicture = null, imageUri = null)
            }

            is BodyScreenIntent.ImageSelected -> {
                state = state.copy(imageUri = event.image)
            }

            is BodyScreenIntent.SaveImage -> {
                // TODO
                if (state.imageUri != null) {

                    val bitmap = MediaStore.Images.Media.getBitmap(
                        event.contentResolver, state.imageUri
                    )

                    viewModelScope.launch {
                        when (val request = uploadImageUseCase.execute(bitmap)) {
                            is Resource.Success -> {
                                withContext(Dispatchers.Main) {
                                    val id = request.data?.id ?: ""
                                    val date = PhotoDateMapper.mapUploadedDateToString(
                                        request.data?.uploaded ?: 0
                                    )
                                    state = state.copy(firstPhoto = Photo(id, date, bitmap))
                                }
                            }

                            else -> {}
                        }

                        state = state.copy(takePicture = null, imageUri = null)
                    }
                }
            }
        }
    }
}