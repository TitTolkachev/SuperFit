package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.repository.local.CredentialsRepository
import javax.inject.Inject

class GetCredentialsFromLocalStorageUseCase @Inject constructor(private val repository: CredentialsRepository) {

    suspend fun execute(): Credentials {

        return repository.getCredentials()
    }
}