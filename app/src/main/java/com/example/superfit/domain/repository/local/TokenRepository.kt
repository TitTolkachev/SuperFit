package com.example.superfit.domain.repository.local

import com.example.superfit.domain.model.Token

interface TokenRepository {

    fun getToken(): Token?

    fun saveToken(token: Token)
}