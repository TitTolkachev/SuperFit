package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BodyParamsDto(
    val weight: Int,
    val height: Int,
    val date: String,
)
