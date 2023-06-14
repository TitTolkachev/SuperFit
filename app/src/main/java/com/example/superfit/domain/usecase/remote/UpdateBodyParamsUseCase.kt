package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.BodyParamsBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class UpdateBodyParamsUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(params: BodyParamsBody): Resource<String> {
        return repository.updateBodyParameters(params)
    }
}