package com.example.superfit.domain.usecase.remote

import android.graphics.Bitmap
import com.example.superfit.domain.model.PhotoInfoBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(image: Bitmap): Resource<PhotoInfoBody> {
        return repository.uploadPhoto(image)
    }
}