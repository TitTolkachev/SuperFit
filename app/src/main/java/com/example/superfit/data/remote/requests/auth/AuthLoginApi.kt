package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.LoginRequestDto
import com.example.superfit.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthLoginApi {

    @POST("api/auth/token")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto
}