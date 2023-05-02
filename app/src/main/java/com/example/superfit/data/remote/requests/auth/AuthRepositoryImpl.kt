package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.RegisterRequestDto
import com.example.superfit.domain.model.RegisterRequestBody
import com.example.superfit.domain.model.RegisterResponseBody
import com.example.superfit.domain.repository.remote.AuthRepository
import com.example.superfit.domain.util.Resource

class AuthRepositoryImpl(private val api: AuthApi) : AuthRepository {

    override suspend fun register(registerRequestBody: RegisterRequestBody): Resource<RegisterResponseBody> {
        return try {
            val data = api.register(
                RegisterRequestDto(
                    registerRequestBody.login,
                    registerRequestBody.password
                )
            )

            if (data.isSuccessful)
                Resource.Success(RegisterResponseBody(data.body()?.message ?: ""))
            else
                Resource.NetworkError(data.message())
        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}