package com.example.superfit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Credentials
import com.example.superfit.domain.usecase.local.GetCredentialsFromLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveCredentialsToLocalStorageUseCase
import com.example.superfit.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.remote.GetProfileUseCase
import com.example.superfit.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val getCredentialsFromLocalStorageUseCase: GetCredentialsFromLocalStorageUseCase,
    private val saveCredentialsToLocalStorageUseCase: SaveCredentialsToLocalStorageUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            //saveTokenToLocalStorageUseCase.execute(Token("", ""))

            val credentials = getCredentialsFromLocalStorageUseCase.execute()

            if (credentials.login.isBlank()) {
                // First Enter
                saveCredentialsToLocalStorageUseCase.execute(Credentials("Vasya@mail.com", "12345"))
            }

            val data = getProfileUseCase.execute()
            if (data is Resource.Success) {
                Log.e("SUCCESS", data.data?.login ?: "")
            } else {
                Log.e("ERROR", data.message.toString())
            }
        }
    }
}