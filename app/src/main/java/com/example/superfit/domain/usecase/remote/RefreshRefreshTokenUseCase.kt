package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.repository.remote.AnotherAuthRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class RefreshRefreshTokenUseCase @Inject constructor(private val repository: AnotherAuthRepository) {

    suspend fun execute(loginRequestBody: LoginRequestBody): Resource<LoginResponseBody> {
        return repository.login(loginRequestBody)
    }
}