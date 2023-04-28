package com.example.superfit.domain.repository.remote

import com.example.superfit.domain.model.RegisterRequestBody
import com.example.superfit.domain.model.RegisterResponseBody
import com.example.superfit.domain.util.Resource

interface AuthRepository {

    suspend fun register(registerRequestBody: RegisterRequestBody): Resource<RegisterResponseBody>
}