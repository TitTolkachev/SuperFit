package com.example.superfit.presentation.view.screens.exercises

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.superfit.R
import com.example.superfit.presentation.view.model.Exercise
import javax.inject.Inject

class ExercisesViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(
        ExercisesScreenState(
            exercises = listOf(
                Exercise(
                    R.drawable.exercise_push_ups_image,
                    R.string.exercises_push_ups_title,
                    R.string.exercises_push_ups_text
                ),
                Exercise(
                    R.drawable.exercise_plank_image,
                    R.string.exercises_plank_title,
                    R.string.exercises_plank_text
                ),
                Exercise(
                    R.drawable.exercise_squats_image,
                    R.string.exercises_squats_title,
                    R.string.exercises_squats_text
                ),
                Exercise(
                    R.drawable.exercise_crunch_image,
                    R.string.exercises_crunch_title,
                    R.string.exercises_crunch_text
                ),
                Exercise(
                    R.drawable.exercise_running_image,
                    R.string.exercises_running_title,
                    R.string.exercises_running_text
                ),
            )
        )
    )
        private set

    fun accept(event: ExercisesScreenUiEvent) {
        when (event) {
            is ExercisesScreenUiEvent.ShowExerciseScreen -> {
                state = state.copy(showExercise = event.exerciseIndex)
            }

            is ExercisesScreenUiEvent.NavigateBack -> {
                state = state.copy(navigateBack = true)
            }

            is ExercisesScreenUiEvent.Navigated -> {
                state = state.copy(
                    showExercise = null,
                    navigateBack = null
                )
            }
        }
    }
}