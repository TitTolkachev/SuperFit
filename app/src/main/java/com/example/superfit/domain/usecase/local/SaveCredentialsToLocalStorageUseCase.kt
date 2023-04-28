package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.repository.local.CredentialsRepository

class SaveCredentialsToLocalStorageUseCase(private val repository: CredentialsRepository) {

    suspend fun execute(credentials: Credentials) {
        return repository.saveCredentials(credentials)
    }
}