package com.example.superfit.data.remote.requests.auth

import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.repository.remote.AuthRefreshRepository
import com.example.superfit.domain.util.Resource

class AuthRefreshRepositoryImpl: AuthRefreshRepository {

    override suspend fun refresh(refreshToken: String): Resource<RefreshResponseBody> {
        TODO("Not yet implemented")
    }
}