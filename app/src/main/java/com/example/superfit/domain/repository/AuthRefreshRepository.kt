package com.example.superfit.domain.repository

import com.example.superfit.domain.model.RefreshResponseBody
import com.example.superfit.domain.util.Resource

interface AuthRefreshRepository {

    suspend fun refresh(refreshToken: String): Resource<RefreshResponseBody>
}