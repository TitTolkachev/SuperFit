package com.example.superfit.data.repository

import com.example.superfit.domain.model.RegisterRequestBody
import com.example.superfit.domain.model.RegisterResponseBody
import com.example.superfit.domain.repository.remote.AuthRepository
import com.example.superfit.domain.util.Resource

class FakeAuthRepository : AuthRepository {

    private val registeredUsers = mutableListOf<Pair<String, Long>>()

    override suspend fun register(registerRequestBody: RegisterRequestBody): Resource<RegisterResponseBody> {

        if (registeredUsers.any { it.first == registerRequestBody.login }) {
            return Resource.NetworkError("User already exists")
        }

        registeredUsers.add(Pair(registerRequestBody.login, registerRequestBody.password))
        return Resource.Success(RegisterResponseBody("Ok"))
    }
}