package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TrainingDto(
    val date: String,
    val exercise: String,
    val repeatCount: Int,
)
