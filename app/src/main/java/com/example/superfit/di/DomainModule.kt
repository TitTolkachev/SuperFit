package com.example.superfit.di

import com.example.superfit.domain.repository.remote.AuthRefreshRepository
import com.example.superfit.domain.usecase.collection.NetworkAuthUseCases
import com.example.superfit.domain.usecase.local.GetCredentialsFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.remote.RefreshAccessTokenUseCase
import com.example.superfit.domain.usecase.remote.RefreshRefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideNetworkAuthUseCases(
        getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
        saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
        getCredentialsFromLocalStorageUseCase: GetCredentialsFromLocalStorageUseCase,
        refreshAccessTokenUseCase: RefreshAccessTokenUseCase,
        refreshRefreshTokenUseCase: RefreshRefreshTokenUseCase
    ): NetworkAuthUseCases {
        return NetworkAuthUseCases(
            getTokenFromLocalStorageUseCase,
            saveTokenToLocalStorageUseCase,
            getCredentialsFromLocalStorageUseCase,
            refreshAccessTokenUseCase,
            refreshRefreshTokenUseCase
        )
    }


    // ---------------
    // ---------------
    // Remote

    @Provides
    fun provideRefreshAccessTokenUseCase(repository: AuthRefreshRepository): RefreshAccessTokenUseCase {
        return RefreshAccessTokenUseCase(repository)
    }

    @Provides
    fun provideRefreshRefreshTokenUseCase(repository: AuthRefreshRepository): RefreshAccessTokenUseCase {
        return RefreshAccessTokenUseCase(repository)
    }


    // ---------------
    // ---------------
    // Local

}