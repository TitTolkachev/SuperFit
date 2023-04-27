package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponseDto(
    val access_token: String,
    val expired: Long,
)
