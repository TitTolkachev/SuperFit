package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.ProfileResponseBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(): Resource<ProfileResponseBody> {
        return repository.getProfile()
    }
}