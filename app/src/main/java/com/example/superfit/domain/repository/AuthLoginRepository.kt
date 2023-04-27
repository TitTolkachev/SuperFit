package com.example.superfit.domain.repository

import com.example.superfit.domain.model.LoginRequestBody
import com.example.superfit.domain.model.LoginResponseBody
import com.example.superfit.domain.util.Resource

interface AuthLoginRepository {

    suspend fun login(registerRequestBody: LoginRequestBody): Resource<LoginResponseBody>
}