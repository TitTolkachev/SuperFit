package com.example.superfit.presentation.view.screens.exercises.crunch

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CrunchViewModel @Inject constructor(
    getTrainingUseCase: GetTrainingUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase
) : ViewModel() {

    var state by mutableStateOf(CrunchScreenState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getTrainingUseCase.execute()) {
                is Resource.Success -> {

                    val data = request.data
                        ?.filter { it.exercise == Exercises.CRUNCH.name }
                        ?.maxByOrNull { it.repeatCount }

                    withContext(Dispatchers.Main) {
                        state = data?.let {
                            state.copy(repeatsCount = it.repeatCount + 5)
                        } ?: state.copy(repeatsCount = 10)
                    }
                }

                else -> {}
            }
        }
    }

    fun accept(event: CrunchScreenIntent) {
        when (event) {
            is CrunchScreenIntent.Finish -> {
                viewModelScope.launch {
                    saveTrainingUseCase.execute(
                        Training(
                            date = DateHelper.getDate(),
                            exercise = Exercises.CRUNCH.name,
                            state.repeatsCount ?: 10
                        )
                    )
                    state = state.copy(navigateToSuccessScreen = true)
                }
            }

            is CrunchScreenIntent.Navigated -> {
                state = state.copy(navigateToSuccessScreen = null)
            }
        }
    }
}