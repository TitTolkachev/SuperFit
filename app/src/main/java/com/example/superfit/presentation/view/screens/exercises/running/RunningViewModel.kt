package com.example.superfit.presentation.view.screens.exercises.running

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RunningViewModel @Inject constructor(
    private val unsubscribeSensorsUseCase: UnsubscribeSensorsUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
    getTrainingUseCase: GetTrainingUseCase,
    subscribeSensorsUseCase: SubscribeSensorsUseCase,
    observeSensorsUseCase: ObserveSensorsUseCase,
) : ViewModel() {

    var state by mutableStateOf(RunningScreenState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getTrainingUseCase.execute()) {
                is Resource.Success -> {

                    val data = request.data
                        ?.filter { it.exercise == Exercises.RUNNING.name }
                        ?.maxByOrNull { it.repeatCount }

                    withContext(Dispatchers.Main) {
                        state = if (data != null && data.repeatCount >= 1000) {
                            state.copy(
                                totalRepeatsCount = ((data.repeatCount + 100) / 100) * 100,
                                repeatsCount = 0
                            )
                        } else state.copy(totalRepeatsCount = 1000, repeatsCount = 0)
                    }
                }

                else -> {}
            }
            subscribeSensorsUseCase.execute(com.example.superfit.domain.model.Exercises.SQUATS)
            observeSensorsUseCase.execute().collect { value ->
                if ((state.totalRepeatsCount ?: 1000) - value <= 0) {
                    saveTrainingUseCase.execute(
                        Training(
                            date = DateHelper.getDate(),
                            exercise = Exercises.RUNNING.name,
                            state.totalRepeatsCount ?: 1000
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

    fun accept(event: RunningScreenIntent) {
        state = when (event) {
            is RunningScreenIntent.Finish -> {


                if (state.repeatsCount != null && state.totalRepeatsCount != null && state.repeatsCount!! >= state.totalRepeatsCount!!)
                    state.copy(navigateToSuccessScreen = true)
                else {
                    if (state.repeatsCount != null && state.repeatsCount!! > 0) {
                        // TODO (Проверить работает ли)
                        CoroutineScope(Dispatchers.IO).launch {
                            saveTrainingUseCase.execute(
                                Training(
                                    date = DateHelper.getDate(),
                                    exercise = Exercises.RUNNING.name,
                                    state.repeatsCount ?: 1000
                                )
                            )
                        }
                    }
                    state.copy(navigateToUnSuccessScreen = true)
                }
            }

            is RunningScreenIntent.Navigated -> {
                state.copy(navigateToUnSuccessScreen = null, navigateToSuccessScreen = null)
            }
        }
    }
}