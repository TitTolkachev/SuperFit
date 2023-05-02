package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.RefreshRequestDto
import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.repository.remote.AuthRefreshRepository
import com.example.superfit.domain.util.Resource

class AuthRefreshRepositoryImpl(private val api: AuthRefreshApi) : AuthRefreshRepository {

    override suspend fun refresh(refreshToken: String): Resource<RefreshResponseBody> {
        return try {
            val request = api.refresh(RefreshRequestDto(refreshToken))
            if (request.isSuccessful) {
                val data = request.body()
                Resource.Success(RefreshResponseBody(data?.access_token ?: "", data?.expired ?: 0))
            } else
                Resource.NetworkError(request.message())
        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}