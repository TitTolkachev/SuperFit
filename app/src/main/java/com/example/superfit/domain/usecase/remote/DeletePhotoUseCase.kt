package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(imageId: String): Resource<String> {
        return repository.deletePhoto(imageId)
    }
}