package com.example.superfit.data.local.prefs.creds

import androidx.datastore.core.Serializer
import com.example.superfit.data.local.prefs.dto.CredentialsDto
import com.example.superfit.data.util.CryptoManager
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class CredentialsSerializer(
    private val cryptoManager: CryptoManager
) : Serializer<CredentialsDto> {

    override val defaultValue: CredentialsDto
        get() = CredentialsDto("", "")

    override suspend fun readFrom(input: InputStream): CredentialsDto {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = CredentialsDto.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: CredentialsDto, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = CredentialsDto.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}