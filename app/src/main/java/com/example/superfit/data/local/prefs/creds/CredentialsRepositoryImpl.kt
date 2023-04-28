package com.example.superfit.data.local.prefs.creds

import com.example.superfit.data.local.prefs.dto.CredentialsDto
import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.repository.local.CredentialsRepository

class CredentialsRepositoryImpl(private val credentialsStorage: CredentialsStorage) :
    CredentialsRepository {

    override suspend fun getCredentials(): Credentials {
        val data = credentialsStorage.getCredentials()
        return Credentials(data.login, data.password)
    }

    override suspend fun saveCredentials(credentials: Credentials) {
        credentialsStorage.saveCredentials(CredentialsDto(credentials.login, credentials.password))
    }
}