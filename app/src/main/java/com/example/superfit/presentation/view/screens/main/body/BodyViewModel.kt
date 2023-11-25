package com.example.superfit.presentation.view.screens.main.body

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.BodyParamsBody
import com.example.superfit.domain.usecase.remote.DownloadPhotoUseCase
import com.example.superfit.domain.usecase.remote.GetHistoryUseCase
import com.example.superfit.domain.usecase.remote.GetPhotosUseCase
import com.example.superfit.domain.usecase.remote.UpdateBodyParamsUseCase
import com.example.superfit.domain.usecase.remote.UploadImageUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.helper.DateHelper
import com.example.superfit.presentation.helper.ImagesHelper
import com.example.superfit.presentation.helper.PhotoDateMapper
import com.example.superfit.presentation.view.model.ErrorType
import com.example.superfit.presentation.view.model.Photo
import com.example.superfit.presentation.view.model.ValidationError
import com.example.superfit.presentation.view.shared.errordialog.ErrorDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BodyViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val updateBodyParamsUseCase: UpdateBodyParamsUseCase,
    getHistoryUseCase: GetHistoryUseCase,
    downloadPhotoUseCase: DownloadPhotoUseCase,
    getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    companion object {
        private const val DEFAULT_WEIGHT = 76
        private const val DEFAULT_HEIGHT = 178
    }

    var state by mutableStateOf(BodyScreenState())
        private set

    var inputDialogState by mutableStateOf(BodyInputDialogState())
        private set

    var errorDialogState by mutableStateOf(ErrorDialogState())
        private set

    init {
        viewModelScope.launch {

            // Body Params
            val bodyHistoryRequest = getHistoryUseCase
                .execute()
            when (bodyHistoryRequest) {
                is Resource.Success -> {
                    val currentBodyParams = bodyHistoryRequest.data?.maxByOrNull { it.date }
                    if (currentBodyParams != null) {
                        withContext(Dispatchers.Main) {
                            state = state.copy(
                                weight = currentBodyParams.weight,
                                height = currentBodyParams.height
                            )
                        }
                    }
                }

                is Resource.NetworkError -> {
                    errorDialogState = errorDialogState.copy(
                        text = bodyHistoryRequest.message,
                        errorType = ErrorType.NETWORK
                    )
                }

                is Resource.Exception -> {
                    errorDialogState = errorDialogState.copy(
                        text = bodyHistoryRequest.message,
                        errorType = ErrorType.UNEXPECTED
                    )
                }
            }

            // Photos
            val photosRequest = getPhotosUseCase
                .execute()
            when (photosRequest) {
                is Resource.Success -> {
                    val photos = photosRequest.data!!
                    var first: Photo? = null
                    var last: Photo? = null
                    if (photos.isNotEmpty()) {
                        val lastPhotoRequest = photos.lastOrNull()
                            ?.let { downloadPhotoUseCase.execute(it.id) }
                        when (lastPhotoRequest) {
                            is Resource.Success -> {
                                last = lastPhotoRequest.data?.let {
                                    Photo(
                                        photos.last().id,
                                        PhotoDateMapper.mapUploadedDateToString(photos.last().uploaded),
                                        it
                                    )
                                }
                            }

                            else -> {}
                        }
                    }
                    if (photos.size > 1) {
                        val firstPhotoRequest = photos
                            .firstOrNull()
                            ?.let { downloadPhotoUseCase.execute(it.id) }
                        when (firstPhotoRequest) {
                            is Resource.Success -> {
                                first = firstPhotoRequest.data?.let {
                                    Photo(
                                        photos.first().id,
                                        PhotoDateMapper.mapUploadedDateToString(photos.first().uploaded),
                                        it
                                    )
                                }
                            }

                            else -> {}
                        }
                    }
                    withContext(Dispatchers.Main) {
                        state = if (first == null)
                            state.copy(firstPhoto = last)
                        else
                            state.copy(firstPhoto = first, lastPhoto = last)
                    }
                }

                is Resource.NetworkError -> {
                    errorDialogState = errorDialogState.copy(
                        text = photosRequest.message,
                        errorType = ErrorType.NETWORK
                    )
                }

                is Resource.Exception -> {
                    errorDialogState = errorDialogState.copy(
                        text = photosRequest.message,
                        errorType = ErrorType.UNEXPECTED
                    )
                }
            }
        }
    }

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
                if (inputDialogState.editWeight != true)
                    inputDialogState = inputDialogState.copy(editHeight = true, text = "")
            }

            BodyScreenIntent.EditWeight -> {
                if (inputDialogState.editHeight != true)
                    inputDialogState = inputDialogState.copy(editWeight = true, text = "")
            }

            BodyScreenIntent.ShowImages -> {
                state = state.copy(showImages = true)
            }

            BodyScreenIntent.ShowStatistics -> {
                state = state.copy(showStatistics = true)
            }

            BodyScreenIntent.ShowTrainProgress -> {
                state = state.copy(showTrainProgress = true)
            }

            BodyScreenIntent.TakePicture -> {
                state = state.copy(takePicture = true)
            }

            is BodyScreenIntent.ShowImage -> {
                state = state.copy(showImage = event.image.copy())
            }

            BodyScreenIntent.CloseDialog -> {
                state = state.copy(takePicture = null, imageUri = null)
            }

            is BodyScreenIntent.ImageSelected -> {
                state = state.copy(imageUri = event.image)
            }

            is BodyScreenIntent.SaveImage -> {
                if (state.imageUri != null && state.imageUri != Uri.EMPTY) {
                    val bitmap = ImagesHelper.getResizedBitmap(event.image) ?: return
                    viewModelScope.launch {
                        val request = uploadImageUseCase
                            .execute(bitmap)
                        when (request) {
                            is Resource.Success -> {
                                withContext(Dispatchers.Main) {
                                    val id = request.data?.id ?: ""
                                    val date = PhotoDateMapper
                                        .mapUploadedDateToString(
                                            request.data?.uploaded ?: 0
                                        )

                                    val resPhoto = Photo(id, date, bitmap)

                                    state = if (state.firstPhoto == null)
                                        state.copy(firstPhoto = resPhoto)
                                    else if (state.lastPhoto == null)
                                        state.copy(lastPhoto = resPhoto)
                                    else
                                        state.copy(lastPhoto = resPhoto)
                                }
                            }

                            is Resource.NetworkError -> {
                                errorDialogState = errorDialogState.copy(
                                    text = request.message,
                                    errorType = ErrorType.NETWORK
                                )
                            }

                            is Resource.Exception -> {
                                errorDialogState = errorDialogState.copy(
                                    text = request.message,
                                    errorType = ErrorType.UNEXPECTED
                                )
                            }
                        }

                        withContext(Dispatchers.Main) {
                            state = state.copy(
                                takePicture = null,
                                imageUri = null
                            )
                        }
                    }
                }
            }

            is BodyScreenIntent.ErrorDialogShowed -> {
                errorDialogState = errorDialogState.copy(text = null)
            }
        }
    }

    fun accept(event: BodyInputDialogIntent) {
        when (event) {
            is BodyInputDialogIntent.NewText -> {
                inputDialogState = inputDialogState.copy(
                    text = event.text.filter { it.isDigit() }
                )
            }

            BodyInputDialogIntent.SaveChanges -> {
                val number: Int
                if (inputDialogState.text.isBlank()) {
                    errorDialogState = errorDialogState.copy(
                        text = "",
                        errorType = ErrorType.VALIDATION,
                        validation = ValidationError.EMPTY_BODY_FIELD
                    )
                    return
                }
                try {
                    number = inputDialogState.text.toInt()
                } catch (e: NumberFormatException) {
                    errorDialogState = errorDialogState.copy(
                        text = "",
                        errorType = ErrorType.VALIDATION,
                        validation = ValidationError.INVALID_INPUT_BODY_FIELD
                    )
                    return
                } catch (_: Exception) {
                    return
                }
                if (number < 10 || number > 300) {
                    errorDialogState = errorDialogState.copy(
                        text = "",
                        errorType = ErrorType.VALIDATION,
                        validation = ValidationError.OUT_OF_BOUNDS_BODY_FIELD
                    )
                    return
                }

                val weight: Int
                val height: Int
                if (inputDialogState.editWeight == true) {
                    weight = number
                    height = state.height ?: DEFAULT_HEIGHT
                } else {
                    weight = state.weight ?: DEFAULT_WEIGHT
                    height = number
                }

                viewModelScope.launch {
                    val requestParams = BodyParamsBody(
                        weight = weight,
                        height = height,
                        date = DateHelper.getDate()
                    )
                    val request = updateBodyParamsUseCase
                        .execute(requestParams)
                    when (request) {
                        is Resource.Success -> {
                            withContext(Dispatchers.Main) {
                                state = state.copy(
                                    weight = weight,
                                    height = height
                                )
                            }
                        }

                        is Resource.NetworkError -> {
                            errorDialogState = errorDialogState.copy(
                                text = request.message,
                                errorType = ErrorType.NETWORK
                            )
                        }

                        is Resource.Exception -> {
                            errorDialogState = errorDialogState.copy(
                                text = request.message,
                                errorType = ErrorType.UNEXPECTED
                            )
                        }
                    }

                    withContext(Dispatchers.Main) {
                        inputDialogState =
                            inputDialogState.copy(
                                editHeight = null,
                                editWeight = null,
                                text = ""
                            )
                    }
                }
            }

            BodyInputDialogIntent.CloseDialog -> {
                inputDialogState =
                    inputDialogState.copy(
                        editHeight = null,
                        editWeight = null,
                        text = ""
                    )
            }
        }
    }
}