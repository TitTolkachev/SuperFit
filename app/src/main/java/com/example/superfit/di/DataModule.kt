package com.example.superfit.di

import android.content.Context
import com.example.superfit.data.local.prefs.creds.CredentialsRepositoryImpl
import com.example.superfit.data.local.prefs.creds.CredentialsStorage
import com.example.superfit.data.local.prefs.creds.CredentialsStorageImpl
import com.example.superfit.data.local.prefs.entrance.FirstEnterRepositoryImpl
import com.example.superfit.data.local.prefs.entrance.FirstEnterStorage
import com.example.superfit.data.local.prefs.entrance.FirstEnterStorageImpl
import com.example.superfit.data.local.prefs.token.TokenRepositoryImpl
import com.example.superfit.data.local.prefs.token.TokenStorage
import com.example.superfit.data.local.prefs.token.TokenStorageImpl
import com.example.superfit.data.remote.Network
import com.example.superfit.data.remote.RefreshNetwork
import com.example.superfit.data.remote.requests.auth.AuthApi
import com.example.superfit.data.remote.requests.auth.AuthLoginApi
import com.example.superfit.data.remote.requests.auth.AuthLoginRepositoryImpl
import com.example.superfit.data.remote.requests.auth.AuthRefreshApi
import com.example.superfit.data.remote.requests.auth.AuthRefreshRepositoryImpl
import com.example.superfit.data.remote.requests.auth.AuthRepositoryImpl
import com.example.superfit.data.remote.requests.profile.ProfileApi
import com.example.superfit.data.remote.requests.profile.ProfileRepositoryImpl
import com.example.superfit.domain.repository.local.CredentialsRepository
import com.example.superfit.domain.repository.local.FirstEnterRepository
import com.example.superfit.domain.repository.local.TokenRepository
import com.example.superfit.domain.repository.remote.AuthLoginRepository
import com.example.superfit.domain.repository.remote.AuthRefreshRepository
import com.example.superfit.domain.repository.remote.AuthRepository
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.usecase.collection.NetworkAuthUseCases
import com.example.superfit.domain.usecase.local.GetCredentialsFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.remote.RefreshAccessTokenUseCase
import com.example.superfit.domain.usecase.remote.RefreshRefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // ---------------
    // ---------------
    // Remote

    @Provides
    @Singleton
    fun provideAuthApi(useCases: NetworkAuthUseCases): AuthApi {
        return Network.getAuthApi(useCases = useCases)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideAuthLoginApi(): AuthLoginApi {
        return RefreshNetwork.getAuthLoginApi()
    }

    @Provides
    @Singleton
    fun provideAuthLoginRepository(api: AuthLoginApi): AuthLoginRepository {
        return AuthLoginRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideAuthRefreshApi(): AuthRefreshApi {
        return RefreshNetwork.getAuthRefreshApi()
    }

    @Provides
    @Singleton
    fun provideAuthRefreshRepository(api: AuthRefreshApi): AuthRefreshRepository {
        return AuthRefreshRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideProfileApi(useCases: NetworkAuthUseCases): ProfileApi {
        return Network.getProfileApi(useCases = useCases)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(api: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(api = api)
    }


    // ---------------
    // ---------------
    // Local

    @Provides
    @Singleton
    fun provideCredentialsStorage(@ApplicationContext context: Context): CredentialsStorage {
        return CredentialsStorageImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideCredentialsRepository(storage: CredentialsStorage): CredentialsRepository {
        return CredentialsRepositoryImpl(credentialsStorage = storage)
    }

    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage {
        return TokenStorageImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(storage: TokenStorage): TokenRepository {
        return TokenRepositoryImpl(tokenStorage = storage)
    }

    @Provides
    @Singleton
    fun provideEntranceStorage(@ApplicationContext context: Context): FirstEnterStorage {
        return FirstEnterStorageImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideEntranceRepository(storage: FirstEnterStorage): FirstEnterRepository {
        return FirstEnterRepositoryImpl(storage = storage)
    }


    // ---------------
    // ---------------
    // UseCases Model

    @Provides
    @Singleton
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
}