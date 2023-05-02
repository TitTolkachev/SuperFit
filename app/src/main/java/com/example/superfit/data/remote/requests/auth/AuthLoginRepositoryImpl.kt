package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.LoginRequestDto
import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.repository.remote.AuthLoginRepository
import com.example.superfit.domain.util.Resource

class AuthLoginRepositoryImpl(private val api: AuthLoginApi) : AuthLoginRepository {

    override suspend fun login(registerRequestBody: LoginRequestBody): Resource<LoginResponseBody> {
        return try {
            val request =
                api.login(LoginRequestDto(registerRequestBody.login, registerRequestBody.password))
            if (request.isSuccessful) {
                val data = request.body()
                Resource.Success(
                    LoginResponseBody(
                        data?.username ?: "",
                        data?.refresh_token ?: "",
                        data?.expired ?: 0
                    )
                )
            } else
                Resource.NetworkError(request.message())
        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}