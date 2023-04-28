package com.example.superfit.domain.usecase.collection

import com.example.superfit.domain.usecase.local.GetCredentialsFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.remote.RefreshAccessTokenUseCase
import com.example.superfit.domain.usecase.remote.RefreshRefreshTokenUseCase

data class NetworkAuthUseCases(
    val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    val getCredentialsFromLocalStorageUseCase: GetCredentialsFromLocalStorageUseCase,
    val refreshAccessTokenUseCase: RefreshAccessTokenUseCase,
    val refreshRefreshTokenUseCase: RefreshRefreshTokenUseCase
)
