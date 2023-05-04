package com.example.superfit.data.remote

import com.example.superfit.data.remote.requests.auth.AuthApi
import com.example.superfit.data.remote.requests.profile.ProfileApi
import com.example.superfit.domain.usecase.collection.NetworkAuthUseCases
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Network {
    private const val BASE_URL = "http://fitness.wsmob.xyz:22169"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun getHttpClient(
        useCases: NetworkAuthUseCases
    ): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
            addInterceptor(MainInterceptor(useCases.getTokenFromLocalStorageUseCase))
            authenticator(TokenAuthenticator(useCases))
        }

        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getAuthRetrofit(
        useCases: NetworkAuthUseCases
    ): Retrofit {

        if (authRetrofit != null)
            return authRetrofit as Retrofit

        authRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient(useCases))
            .build()

        return authRetrofit as Retrofit
    }

    private var authRetrofit: Retrofit? = null

    fun getAuthApi(useCases: NetworkAuthUseCases): AuthApi =
        getAuthRetrofit(useCases).create(AuthApi::class.java)

    fun getProfileApi(useCases: NetworkAuthUseCases): ProfileApi =
        getAuthRetrofit(useCases).create(ProfileApi::class.java)
}