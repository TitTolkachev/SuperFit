package com.example.superfit.data.local.prefs.token

import android.content.Context
import android.content.SharedPreferences
import com.example.superfit.data.local.prefs.dto.TokenDto

private const val APP_PREFERENCES = "preferences_settings"
private const val ACCESS_TOKEN_NAME = "access_token"
private const val REFRESH_TOKEN_NAME = "refresh_token"

class TokenStorageImpl(context: Context): TokenStorage {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

    override fun getToken(): TokenDto? {

        val accessToken = preferences.getString(ACCESS_TOKEN_NAME, null)
        val refreshToken = preferences.getString(REFRESH_TOKEN_NAME, null)
        return if (accessToken != null && refreshToken != null)
            TokenDto(accessToken, refreshToken)
        else null
    }

    override fun saveToken(token: TokenDto) {
        preferences.edit().putString(ACCESS_TOKEN_NAME, token.accessToken).apply()
        preferences.edit().putString(REFRESH_TOKEN_NAME, token.refreshToken).apply()
    }
}