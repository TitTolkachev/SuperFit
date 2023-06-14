package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.BodyParamsBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(): Resource<List<BodyParamsBody>> {
        return repository.getHistory()
    }
}