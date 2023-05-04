package com.example.superfit.data.remote

import com.example.superfit.data.remote.requests.auth.AuthLoginApi
import com.example.superfit.data.remote.requests.auth.AuthRefreshApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RefreshNetwork {
    private const val BASE_URL = "http://fitness.wsmob.xyz:22169"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }

        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(): Retrofit {

        if (retrofit != null)
            return retrofit as Retrofit

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient())
            .build()

        return retrofit as Retrofit
    }

    private var retrofit: Retrofit? = null

    fun getAuthLoginApi(): AuthLoginApi =
        getRetrofit().create(AuthLoginApi::class.java)

    fun getAuthRefreshApi(): AuthRefreshApi =
        getRetrofit().create(AuthRefreshApi::class.java)
}