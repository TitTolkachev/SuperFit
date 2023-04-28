package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.repository.remote.AuthLoginRepository
import com.example.superfit.domain.util.Resource

class RefreshRefreshTokenUseCase (private val repository: AuthLoginRepository) {

    suspend fun execute(loginRequestBody: LoginRequestBody): Resource<LoginResponseBody> {
        return repository.login(loginRequestBody)
    }
}