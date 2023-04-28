package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.RefreshNetwork
import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.repository.remote.AuthLoginRepository
import com.example.superfit.domain.util.Resource

class AuthLoginRepositoryImpl: AuthLoginRepository {

    // TODO()
    private val api = RefreshNetwork

    override suspend fun login(registerRequestBody: LoginRequestBody): Resource<LoginResponseBody> {
        return try {
            TODO()
        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}