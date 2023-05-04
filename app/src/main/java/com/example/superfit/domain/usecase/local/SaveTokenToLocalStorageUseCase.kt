package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.model.Token
import com.example.superfit.domain.repository.local.TokenRepository
import javax.inject.Inject

class SaveTokenToLocalStorageUseCase @Inject constructor(private val repository: TokenRepository) {

    fun execute(token: Token) {
        return repository.saveToken(token)
    }
}