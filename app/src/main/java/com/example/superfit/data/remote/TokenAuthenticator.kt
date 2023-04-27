package com.example.superfit.data.remote

import com.example.superfit.domain.model.Token
import com.example.superfit.domain.usecase.collection.NetworkAuthUseCases
import com.example.superfit.domain.util.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val useCases: NetworkAuthUseCases
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

        val localToken = useCases.getTokenFromLocalStorageUseCase.execute()

        if (localToken != null) {

            var remoteAccessToken: String? = null
            var remoteRefreshToken: String? = null

            runBlocking {
                when (val result =
                    useCases.refreshAccessTokenUseCase.execute(localToken.refreshToken)) {
                    is Resource.Success<*> ->
                        remoteAccessToken = result.accessToken

                    else -> {
                        val refreshResult = useCases.refreshRefreshTokenUseCase.execute()
                        if (refreshResult is Resource.Success<*>) {
                            remoteRefreshToken = refreshResult.refreshToken

                            val newResult =
                                useCases.refreshAccessTokenUseCase.execute(localToken.refreshToken)

                            remoteAccessToken = if (newResult is Resource.Success<*>)
                                newResult.accessToken
                            else
                                ""
                        } else
                            remoteRefreshToken = ""
                    }
                }
            }

            useCases.saveTokenToLocalStorageUseCase.execute(
                Token(
                    remoteAccessToken ?: "",
                    remoteRefreshToken ?: localToken.refreshToken
                )
            )

            return if (response.responseCount > 1) {
                null
            } else {
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${remoteAccessToken ?: ""}")
                    .build()
            }
        }
        return null
    }

    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()
}