package com.example.superfit.presentation.view.screens.main.exercises

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.superfit.presentation.view.model.EXERCISES
import javax.inject.Inject

class ExercisesViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(ExercisesScreenState(exercises = EXERCISES))
        private set

    fun accept(event: ExercisesScreenIntent) {
        when (event) {
            is ExercisesScreenIntent.ShowExerciseScreen -> {
                state = state.copy(showExercise = event.exercise)
            }

            is ExercisesScreenIntent.NavigateBack -> {
                state = state.copy(navigateBack = true)
            }

            is ExercisesScreenIntent.Navigated -> {
                state = state.copy(
                    showExercise = null,
                    navigateBack = null
                )
            }
        }
    }
}