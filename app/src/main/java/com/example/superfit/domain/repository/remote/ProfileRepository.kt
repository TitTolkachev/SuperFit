package com.example.superfit.domain.repository.remote

import com.example.superfit.domain.model.ProfileResponseBody
import com.example.superfit.domain.util.Resource

interface ProfileRepository {

    suspend fun getProfile(): Resource<ProfileResponseBody>
}