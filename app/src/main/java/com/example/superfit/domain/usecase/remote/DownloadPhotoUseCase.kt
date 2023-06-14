package com.example.superfit.domain.usecase.remote

import android.graphics.Bitmap
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class DownloadPhotoUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(imageId: String): Resource<Bitmap> {
        return repository.downloadPhoto(imageId)
    }
}