package com.example.superfit.presentation.view.screens.exercises.plank

import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Training
import com.example.superfit.domain.usecase.remote.GetTrainingUseCase
import com.example.superfit.domain.usecase.remote.SaveTrainingUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.helper.DateHelper
import com.example.superfit.presentation.view.model.Exercises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlankViewModel @Inject constructor(
    private val saveTrainingUseCase: SaveTrainingUseCase,
    getTrainingUseCase: GetTrainingUseCase,
) : ViewModel() {

    var state by mutableStateOf(PlankScreenState())
        private set

    var dialogState by mutableStateOf(ExerciseStartDialogState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getTrainingUseCase.execute()) {
                is Resource.Success -> {

                    val data = request.data
                        ?.filter { it.exercise == Exercises.PLANK.name }
                        ?.maxByOrNull { it.repeatCount }

                    withContext(Dispatchers.Main) {
                        if (data != null && data.repeatCount >= 20) {
                            state = state.copy(
                                totalRepeatsCount = ((data.repeatCount + 5) / 5) * 5,
                                repeatsCount = 0
                            )
                            dialogState = dialogState.copy(
                                repeats = ((data.repeatCount + 5) / 5) * 5,
                                isActive = true
                            )
                        } else {
                            state = state.copy(totalRepeatsCount = 20, repeatsCount = 0)
                            dialogState = dialogState.copy(
                                repeats = 20,
                                isActive = true
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }

    fun accept(event: PlankScreenIntent) {
        state = when (event) {
            is PlankScreenIntent.Finish -> {
                if (state.repeatsCount != null && state.totalRepeatsCount != null && state.repeatsCount!! >= state.totalRepeatsCount!!)
                    state.copy(navigateToSuccessScreen = true)
                else {
                    if (state.repeatsCount != null && state.repeatsCount!! > 0) {
                        CoroutineScope(Dispatchers.IO).launch {
                            saveTrainingUseCase.execute(
                                Training(
                                    date = DateHelper.getDate(),
                                    exercise = Exercises.PLANK.name,
                                    state.repeatsCount ?: 20
                                )
                            )
                        }
                    }
                    state.copy(navigateToUnSuccessScreen = true)
                }
            }

            is PlankScreenIntent.Navigated -> {
                state.copy(navigateToUnSuccessScreen = null, navigateToSuccessScreen = null)
            }
        }
    }

    fun dialogAccept(event: ExerciseStartDialogIntent) {
        when (event) {
            is ExerciseStartDialogIntent.Start -> {
                dialogState = dialogState.copy(isActive = false)
                startTimer()
            }

            is ExerciseStartDialogIntent.Cancel -> {
                dialogState = dialogState.copy(isActive = false, exit = true)
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            val startTime = SystemClock.uptimeMillis()
            var time: Long
            if (state.repeatsCount != null && state.totalRepeatsCount != null) {
                while (state.repeatsCount!! < state.totalRepeatsCount!!) {
                    time = ((SystemClock.uptimeMillis() - startTime) / 1000).coerceAtLeast(0)
                    if ((time / 1).toInt() > state.repeatsCount!!) {
                        withContext(Dispatchers.Main) {
                            state = state.copy(repeatsCount = (time / 1).toInt())
                        }
                    }
                    delay(100L)
                }
                CoroutineScope(Dispatchers.IO).launch {
                    saveTrainingUseCase.execute(
                        Training(
                            date = DateHelper.getDate(),
                            exercise = Exercises.PLANK.name,
                            state.totalRepeatsCount!!
                        )
                    )
                }
                state = state.copy(navigateToSuccessScreen = true)
            }
        }
    }
}