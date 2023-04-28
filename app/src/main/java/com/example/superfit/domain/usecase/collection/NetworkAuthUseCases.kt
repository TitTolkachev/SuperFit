package com.example.superfit.domain.usecase.collection

data class NetworkAuthUseCases(
    val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    val getCredentialsFromLocalStorageUseCase: GetCredentialsFromLocalStorageUseCase,
    val saveCredentialsToLocalStorageUseCase: SaveCredentialsToLocalStorageUseCase,
    val refreshAccessTokenUseCase: RefreshAccessTokenUseCase,
    val refreshRefreshTokenUseCase: RefreshRefreshTokenUseCase
)
