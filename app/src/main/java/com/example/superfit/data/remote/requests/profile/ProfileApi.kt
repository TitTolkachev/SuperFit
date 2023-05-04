package com.example.superfit.data.remote.requests.profile

import com.example.superfit.data.remote.dto.ProfileResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {

    @GET("/api/profile")
    suspend fun getProfile(): Response<ProfileResponseDto>
}