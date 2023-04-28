package com.example.superfit.data.local.prefs.creds

import com.example.superfit.data.local.prefs.dto.CredentialsDto

interface CredentialsStorage {

    suspend fun getCredentials(): CredentialsDto

    suspend fun saveCredentials(credentials: CredentialsDto)
}