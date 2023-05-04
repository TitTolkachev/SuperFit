package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.RegisterRequestDto
import com.example.superfit.data.remote.dto.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/auth/register")
    suspend fun register(@Body registerRequestDto: RegisterRequestDto): Response<RegisterResponseDto>
}