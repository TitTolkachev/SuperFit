package com.example.superfit.data.local.prefs.creds

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.superfit.data.local.prefs.dto.CredentialsDto

class CredentialsStorageImpl(context: Context) : CredentialsStorage {

    companion object {
        private const val FILE_NAME = "credentials"
        private const val LOGIN_NAME = "login_name"
        private const val PASSWORD_NAME = "password_name"
    }

    private var masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private var editor = sharedPreferences.edit()

    override suspend fun getCredentials(): CredentialsDto {
        val login = sharedPreferences.getString(LOGIN_NAME, "")
        val password = sharedPreferences.getString(PASSWORD_NAME, "")
        return CredentialsDto(login ?: "", password ?: "")
    }

    override suspend fun saveCredentials(credentials: CredentialsDto) {
        editor.putString(LOGIN_NAME, credentials.login)
        editor.putString(PASSWORD_NAME, credentials.password)
        editor.apply()
    }
}