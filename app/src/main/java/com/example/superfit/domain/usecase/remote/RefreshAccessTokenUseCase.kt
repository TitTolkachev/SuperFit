package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.repository.remote.AnotherAuthRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class RefreshAccessTokenUseCase @Inject constructor(private val repository: AnotherAuthRepository) {

    suspend fun execute(refreshToken: String): Resource<RefreshResponseBody> {
        return repository.refresh(refreshToken)
    }
}