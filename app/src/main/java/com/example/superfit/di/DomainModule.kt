package com.example.superfit.di

import com.example.superfit.domain.repository.local.CredentialsRepository
import com.example.superfit.domain.repository.local.FirstEnterRepository
import com.example.superfit.domain.repository.local.TokenRepository
import com.example.superfit.domain.repository.remote.AnotherAuthRepository
import com.example.superfit.domain.repository.remote.AuthRepository
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.usecase.local.GetCredentialsFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.GetEntranceInfoUseCase
import com.example.superfit.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveCredentialsToLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveEntranceInfoUseCase
import com.example.superfit.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.remote.GetProfileUseCase
import com.example.superfit.domain.usecase.remote.RefreshAccessTokenUseCase
import com.example.superfit.domain.usecase.remote.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    // ---------------
    // ---------------
    // Remote

    @Provides
    fun provideRefreshAccessTokenUseCase(repository: AnotherAuthRepository): RefreshAccessTokenUseCase {
        return RefreshAccessTokenUseCase(repository)
    }

    @Provides
    fun provideRefreshRefreshTokenUseCase(repository: AnotherAuthRepository): RefreshAccessTokenUseCase {
        return RefreshAccessTokenUseCase(repository)
    }

    @Provides
    fun provideGetProfileUseCase(repository: ProfileRepository): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }

    @Provides
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }


    // ---------------
    // ---------------
    // Local

    @Provides
    fun provideGetCredentialsFromLocalStorageUseCase(repository: CredentialsRepository): GetCredentialsFromLocalStorageUseCase {
        return GetCredentialsFromLocalStorageUseCase(repository)
    }

    @Provides
    fun provideGetTokenFromLocalStorageUseCase(repository: TokenRepository): GetTokenFromLocalStorageUseCase {
        return GetTokenFromLocalStorageUseCase(repository)
    }

    @Provides
    fun provideSaveCredentialsToLocalStorageUseCase(repository: CredentialsRepository): SaveCredentialsToLocalStorageUseCase {
        return SaveCredentialsToLocalStorageUseCase(repository)
    }

    @Provides
    fun provideSaveTokenToLocalStorageUseCase(repository: TokenRepository): SaveTokenToLocalStorageUseCase {
        return SaveTokenToLocalStorageUseCase(repository)
    }

    @Provides
    fun provideSaveEntranceInfoUseCase(repository: FirstEnterRepository): SaveEntranceInfoUseCase {
        return SaveEntranceInfoUseCase(repository)
    }

    @Provides
    fun provideGetEntranceInfoUseCase(repository: FirstEnterRepository): GetEntranceInfoUseCase {
        return GetEntranceInfoUseCase(repository)
    }
}