package com.example.superfit.presentation.view.screens.exercises.pushups

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
class PushUpsViewModel @Inject constructor(
    private val unsubscribeSensorsUseCase: UnsubscribeSensorsUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
    getTrainingUseCase: GetTrainingUseCase,
    subscribeSensorsUseCase: SubscribeSensorsUseCase,
    observeSensorsUseCase: ObserveSensorsUseCase,
) : ViewModel() {

    var state by mutableStateOf(PushUpsScreenState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getTrainingUseCase.execute()) {
                is Resource.Success -> {

                    val data = request.data
                        ?.filter { it.exercise == Exercises.PUSH_UP.name }
                        ?.maxByOrNull { it.repeatCount }

                    withContext(Dispatchers.Main) {
                        state = if (data != null && data.repeatCount >= 10) {
                            state.copy(
                                totalRepeatsCount = ((data.repeatCount + 5) / 5) * 5,
                                repeatsCount = 0
                            )
                        } else state.copy(totalRepeatsCount = 10, repeatsCount = 0)
                    }
                }

                else -> {}
            }
            subscribeSensorsUseCase.execute(com.example.superfit.domain.model.Exercises.PUSH_UP)
            observeSensorsUseCase.execute().collect { value ->
                if ((state.totalRepeatsCount ?: 10) - value <= 0) {
                    saveTrainingUseCase.execute(
                        Training(
                            date = DateHelper.getDate(),
                            exercise = Exercises.PUSH_UP.name,
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

    fun accept(event: PushUpsScreenIntent) {
        state = when (event) {
            is PushUpsScreenIntent.Finish -> {

                unsubscribeSensorsUseCase.execute()
                if (state.repeatsCount != null && state.totalRepeatsCount != null && state.repeatsCount!! >= state.totalRepeatsCount!!)
                    state.copy(navigateToSuccessScreen = true)
                else {
                    if (state.repeatsCount != null && state.repeatsCount!! > 0) {
                        CoroutineScope(Dispatchers.IO).launch {
                            saveTrainingUseCase.execute(
                                Training(
                                    date = DateHelper.getDate(),
                                    exercise = Exercises.PUSH_UP.name,
                                    state.repeatsCount ?: 10
                                )
                            )
                        }
                    }
                    state.copy(navigateToUnSuccessScreen = true)
                }
            }

            is PushUpsScreenIntent.Navigated -> {
                state.copy(navigateToUnSuccessScreen = null, navigateToSuccessScreen = null)
            }
        }
    }
}