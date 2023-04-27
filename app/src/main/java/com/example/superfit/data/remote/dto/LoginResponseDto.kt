package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val username: String,
    val refresh_token: String,
    val expired: Long
)
