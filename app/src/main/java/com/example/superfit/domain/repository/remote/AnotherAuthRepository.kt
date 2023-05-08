package com.example.superfit.domain.repository.remote

import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.util.Resource

interface AnotherAuthRepository {

    suspend fun login(registerRequestBody: LoginRequestBody): Resource<LoginResponseBody>

    suspend fun refresh(refreshToken: String): Resource<RefreshResponseBody>
}