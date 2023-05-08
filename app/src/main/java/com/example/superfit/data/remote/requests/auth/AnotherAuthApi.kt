package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.LoginRequestDto
import com.example.superfit.data.remote.dto.LoginResponseDto
import com.example.superfit.data.remote.dto.RefreshRequestDto
import com.example.superfit.data.remote.dto.RefreshResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AnotherAuthApi {

    @POST("/api/auth/token")
    suspend fun login(@Body body: LoginRequestDto): Response<LoginResponseDto>

    @POST("/api/auth/token/refresh")
    suspend fun refresh(@Body refreshRequestDto: RefreshRequestDto): Response<RefreshResponseDto>
}