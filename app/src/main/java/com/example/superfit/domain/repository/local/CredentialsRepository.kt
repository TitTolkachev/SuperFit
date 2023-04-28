package com.example.superfit.domain.repository.local

import com.example.superfit.domain.model.Credentials

interface CredentialsRepository {

    suspend fun getCredentials(): Credentials

    suspend fun saveCredentials(credentials: Credentials)
}