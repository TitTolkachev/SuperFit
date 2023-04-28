package com.example.superfit.data.local.prefs.token

import com.example.superfit.data.local.prefs.dto.TokenDto
import com.example.superfit.domain.model.Token
import com.example.superfit.domain.repository.local.TokenRepository

class TokenRepositoryImpl(private val tokenStorage: TokenStorage) : TokenRepository {

    override fun getToken(): Token? {
        val data = tokenStorage.getToken()
        return if (data != null)
            Token(data.accessToken, data.refreshToken)
        else null
    }

    override fun saveToken(token: Token) {
        tokenStorage.saveToken(TokenDto(token.accessToken, token.refreshToken))
    }
}