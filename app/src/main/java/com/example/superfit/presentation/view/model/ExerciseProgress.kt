package com.example.superfit.presentation.view.model

data class ExerciseProgress(
    val type: Exercises,
    val lastTrainRepeats: Int? = null,
    val progress: Int? = null
)
