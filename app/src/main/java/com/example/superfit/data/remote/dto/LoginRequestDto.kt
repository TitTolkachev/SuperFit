package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val login: String,
    val password: String
)
