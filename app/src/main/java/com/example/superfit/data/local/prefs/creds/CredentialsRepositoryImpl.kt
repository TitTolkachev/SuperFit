package com.example.superfit.data.local.prefs.creds

import com.example.superfit.data.local.prefs.dto.CredentialsDto
import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.repository.local.CredentialsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CredentialsRepositoryImpl(private val credentialsStorage: CredentialsStorage) :
    CredentialsRepository {

    override suspend fun getCredentials(): Credentials {
        return withContext(Dispatchers.IO) {
            val data = credentialsStorage.getCredentials()
            Credentials(data.login, data.password)
        }
    }

    override suspend fun saveCredentials(credentials: Credentials) {
        withContext(Dispatchers.IO) {
            credentialsStorage.saveCredentials(
                CredentialsDto(
                    credentials.login,
                    credentials.password
                )
            )
        }
    }
}