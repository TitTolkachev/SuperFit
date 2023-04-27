package com.example.superfit.domain.model

data class LoginResponseBody(
    val username: String,
    val refreshToken: String,
    val expired: Long
)
