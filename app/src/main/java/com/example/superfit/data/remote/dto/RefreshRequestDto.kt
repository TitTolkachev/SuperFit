package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequestDto(
    val refresh_token: String,
)
