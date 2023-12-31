package com.example.superfit.presentation.view.screens.exercises.squats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Training
import com.example.superfit.domain.usecase.remote.GetTrainingUseCase
import com.example.superfit.domain.usecase.remote.SaveTrainingUseCase
import com.example.superfit.domain.usecase.sensors.ObserveSensorsUseCase
import com.example.superfit.domain.usecase.sensors.SubscribeSensorsUseCase
import com.example.superfit.domain.usecase.sensors.UnsubscribeSensorsUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.helper.DateHelper
import com.example.superfit.presentation.view.model.Exercises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SquatsViewModel @Inject constructor(
    getTrainingUseCase: GetTrainingUseCase,
    saveTrainingUseCase: SaveTrainingUseCase,
    subscribeSensorsUseCase: SubscribeSensorsUseCase,
    observeSensorsUseCase: ObserveSensorsUseCase,
    private val unsubscribeSensorsUseCase: UnsubscribeSensorsUseCase,
) : ViewModel() {

    var state by mutableStateOf(SquatsScreenState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getTrainingUseCase.execute()) {
                is Resource.Success -> {

                    val data = request.data
                        ?.filter { it.exercise == Exercises.SQUATS.name }
                        ?.maxByOrNull { it.repeatCount }

                    withContext(Dispatchers.Main) {
                        state = data?.let {
                            state.copy(
                                totalRepeatsCount = it.repeatCount + 5,
                                repeatsCount = 0
                            )
                        } ?: state.copy(totalRepeatsCount = 10, repeatsCount = 0)
                    }
                }

                else -> {}
            }
            subscribeSensorsUseCase.execute(com.example.superfit.domain.model.Exercises.SQUATS)
            observeSensorsUseCase.execute().collect { value ->
                if ((state.totalRepeatsCount ?: 10) - value <= 0) {
                    saveTrainingUseCase.execute(
                        Training(
                            date = DateHelper.getDate(),
                            exercise = Exercises.SQUATS.name,
                            state.totalRepeatsCount ?: 10
                        )
                    )
                    withContext(Dispatchers.Main) {
                        state = state.copy(
                            repeatsCount = state.totalRepeatsCount,
                            navigateToSuccessScreen = true
                        )
                    }
                    unsubscribeSensorsUseCase.execute()
                } else {
                    withContext(Dispatchers.Main) {
                        state = state.copy(repeatsCount = value)
                    }
                }
            }
        }
    }

    fun accept(event: SquatsScreenIntent) {
        state = when (event) {
            is SquatsScreenIntent.NavigateBack -> {
                unsubscribeSensorsUseCase.execute()
                state.copy(navigateBack = true)
            }

            is SquatsScreenIntent.Navigated -> {
                state.copy(navigateBack = null, navigateToSuccessScreen = null)
            }
        }
    }
}