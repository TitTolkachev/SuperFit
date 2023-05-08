package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.LoginRequestDto
import com.example.superfit.data.remote.dto.RefreshRequestDto
import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.repository.remote.AnotherAuthRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class AnotherAuthRepositoryImpl @Inject constructor(private val api: AnotherAuthApi) : AnotherAuthRepository {

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