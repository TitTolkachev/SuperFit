package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.RegisterRequestBody
import com.example.superfit.domain.model.RegisterResponseBody
import com.example.superfit.domain.repository.remote.AuthRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun execute(body: RegisterRequestBody): Resource<RegisterResponseBody> {

        return repository.register(body)
    }
}