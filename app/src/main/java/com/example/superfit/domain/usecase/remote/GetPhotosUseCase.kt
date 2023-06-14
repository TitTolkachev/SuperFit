package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.PhotoInfoBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(): Resource<List<PhotoInfoBody>> {
        return repository.getPhotos()
    }
}