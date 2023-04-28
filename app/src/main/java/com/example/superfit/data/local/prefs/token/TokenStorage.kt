package com.example.superfit.data.local.prefs.token

import com.example.superfit.data.local.prefs.dto.TokenDto

interface TokenStorage {

    fun getToken(): TokenDto?

    fun saveToken(token: TokenDto)
}