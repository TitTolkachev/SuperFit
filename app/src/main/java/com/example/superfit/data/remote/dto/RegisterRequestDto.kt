package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val login:String,
    val password: Long
)
