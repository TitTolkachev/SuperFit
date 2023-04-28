package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.model.Token
import com.example.superfit.domain.repository.local.TokenRepository

class SaveTokenToLocalStorageUseCase(private val repository: TokenRepository) {

    fun execute(token: Token) {
        return repository.saveToken(token)
    }
}