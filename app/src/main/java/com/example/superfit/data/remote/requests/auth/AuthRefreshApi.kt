package com.example.superfit.data.remote.requests.auth

import com.example.superfit.data.remote.dto.RefreshRequestDto
import com.example.superfit.data.remote.dto.RefreshResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRefreshApi {

    @POST("api/auth/token/refresh")
    suspend fun refresh(@Body refreshRequestDto: RefreshRequestDto): RefreshResponseDto
}