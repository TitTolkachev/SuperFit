package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.repository.local.CredentialsRepository

class GetCredentialsFromLocalStorageUseCase(private val repository: CredentialsRepository) {

    suspend fun execute(): Credentials {
        return repository.getCredentials()
    }
}