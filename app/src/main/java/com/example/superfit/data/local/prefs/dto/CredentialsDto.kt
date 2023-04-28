package com.example.superfit.data.local.prefs.dto

import kotlinx.serialization.Serializable

@Serializable
data class CredentialsDto(
    val login: String,
    val password: String,
)