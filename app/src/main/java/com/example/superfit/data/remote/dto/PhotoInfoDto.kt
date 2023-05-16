package com.example.superfit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PhotoInfoDto(
    val id: String,
    val uploaded: Long,
)
