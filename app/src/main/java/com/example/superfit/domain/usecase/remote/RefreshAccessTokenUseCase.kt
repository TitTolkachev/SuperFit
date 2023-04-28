package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.repository.remote.AuthRefreshRepository
import com.example.superfit.domain.util.Resource

class RefreshAccessTokenUseCase(private val repository: AuthRefreshRepository) {

    suspend fun execute(refreshToken: String): Resource<RefreshResponseBody> {
        return repository.refresh(refreshToken)
    }
}