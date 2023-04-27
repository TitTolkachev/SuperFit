package com.example.superfit.domain.model

data class RefreshResponseBody(
    val accessToken: String,
    val expired: Long
)
