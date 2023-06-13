package com.example.superfit.presentation.view.screens.auth.launch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.usecase.local.GetEntranceInfoUseCase
import com.example.superfit.domain.usecase.remote.GetProfileUseCase
import com.example.superfit.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getEntranceInfoUseCase: GetEntranceInfoUseCase
) : ViewModel() {

    var state by mutableStateOf(LaunchScreenState(null, null))
        private set

    init {
        viewModelScope.launch {
            val isFirstEnter = !getEntranceInfoUseCase.execute()

            state = if (isFirstEnter) {
                state.copy(isFirstEnter = true)
            } else {
                val data = getProfileUseCase.execute()
                state.copy(isAuthorized = (data is Resource.Success))
            }
        }
    }
}