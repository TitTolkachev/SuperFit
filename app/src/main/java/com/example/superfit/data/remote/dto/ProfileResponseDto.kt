package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponseDto(
    val login: String?
)
