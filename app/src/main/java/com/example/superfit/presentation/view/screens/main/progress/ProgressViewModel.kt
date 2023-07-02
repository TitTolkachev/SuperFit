package com.example.superfit.presentation.view.screens.main.progress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.domain.model.Training
import com.example.superfit.domain.usecase.remote.GetTrainingUseCase
import com.example.superfit.domain.util.Resource
import com.example.superfit.presentation.view.model.ExerciseProgress
import com.example.superfit.presentation.view.model.Exercises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    getTrainingUseCase: GetTrainingUseCase
) : ViewModel() {

    var state by mutableStateOf(ProgressScreenState())
        private set

    init {
        viewModelScope.launch {
            when (val request = getTrainingUseCase.execute()) {
                is Resource.Success -> {
                    val data = request.data?.sortedByDescending { it.date }

                    val pushUps = data?.filter { it.exercise == Exercises.PUSH_UP.name }?.take(2)
                    val plank = data?.filter { it.exercise == Exercises.PLANK.name }?.take(2)
                    val crunch = data?.filter { it.exercise == Exercises.CRUNCH.name }?.take(2)
                    val squats = data?.filter { it.exercise == Exercises.SQUATS.name }?.take(2)
                    val running = data?.filter { it.exercise == Exercises.RUNNING.name }?.take(2)

                    withContext(Dispatchers.Main) {
                        val exercises = mutableListOf<ExerciseProgress>()
                        pushUps?.let {
                            exercises.add(mapTrainingToExerciseForDrawing(pushUps))
                        }
                        plank?.let {
                            exercises.add(mapTrainingToExerciseForDrawing(plank))
                        }
                        crunch?.let {
                            exercises.add(mapTrainingToExerciseForDrawing(crunch))
                        }
                        squats?.let {
                            exercises.add(mapTrainingToExerciseForDrawing(squats))
                        }
                        running?.let {
                            exercises.add(mapTrainingToExerciseForDrawing(running))
                        }
                        state = state.copy(exercises = exercises)
                    }
                }

                else -> {}
            }
        }
    }

    fun accept(event: ProgressScreenIntent) {
        state = when (event) {
            is ProgressScreenIntent.NavigateBack -> {
                state.copy(navigateBack = true)
            }

            is ProgressScreenIntent.Navigated -> {
                state.copy(navigateBack = null)
            }
        }
    }

    private fun mapTrainingToExerciseForDrawing(listTraining: List<Training>): ExerciseProgress {

        val type = when (listTraining.first().exercise) {
            Exercises.PUSH_UP.name -> Exercises.PUSH_UP

            Exercises.PLANK.name -> Exercises.PLANK

            Exercises.CRUNCH.name -> Exercises.CRUNCH

            Exercises.SQUATS.name -> Exercises.SQUATS

            Exercises.RUNNING.name -> Exercises.RUNNING

            else -> Exercises.PUSH_UP
        }

        val progress = if (listTraining.size == 2) {
            val first = listTraining.first().repeatCount
            val second = listTraining[1].repeatCount
            if (second == 0) 10000
            else (first * 100) / second - 100
        } else null

        return ExerciseProgress(
            type = type,
            lastTrainRepeats = listTraining.first().repeatCount,
            progress = progress
        )
    }
}