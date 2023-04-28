package com.example.superfit.data.local.prefs.creds

import android.content.Context
import androidx.datastore.dataStore
import com.example.superfit.data.local.prefs.dto.CredentialsDto
import com.example.superfit.data.util.CryptoManager
import kotlinx.coroutines.flow.first

private const val FILE_NAME = "credentials.json"

private val Context.dataStore by dataStore(
    fileName = FILE_NAME,
    serializer = CredentialsSerializer(CryptoManager())
)

class CredentialsStorageImpl(context: Context) : CredentialsStorage {

    private val dataStore = context.dataStore

    override suspend fun getCredentials(): CredentialsDto {
        val data = dataStore.data.first()

        val login = data.login
        val password = data.password
        return CredentialsDto(login, password)
    }

    override suspend fun saveCredentials(credentials: CredentialsDto) {
        dataStore.updateData {
            CredentialsDto(credentials.login, credentials.password)
        }
    }
}