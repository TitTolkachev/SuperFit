package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.model.Token
import com.example.superfit.domain.repository.local.TokenRepository

class GetTokenFromLocalStorageUseCase(private val repository: TokenRepository) {

    fun execute(): Token? {
        return repository.getToken()
    }
}