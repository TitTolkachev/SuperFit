package com.example.superfit.presentation.view.model

import com.example.superfit.R

enum class Exercises {
    PUSH_UPS,
    PLANK,
    SQUATS,
    CRUNCH,
    RUNNING,
}

val EXERCISES = listOf(
    Exercise(
        Exercises.PUSH_UPS,
        R.drawable.exercise_push_ups_image,
        R.string.exercises_push_ups_title,
        R.string.exercises_push_ups_text
    ),
    Exercise(
        Exercises.PLANK,
        R.drawable.exercise_plank_image,
        R.string.exercises_plank_title,
        R.string.exercises_plank_text
    ),
    Exercise(
        Exercises.SQUATS,
        R.drawable.exercise_squats_image,
        R.string.exercises_squats_title,
        R.string.exercises_squats_text
    ),
    Exercise(
        Exercises.CRUNCH,
        R.drawable.exercise_crunch_image,
        R.string.exercises_crunch_title,
        R.string.exercises_crunch_text
    ),
    Exercise(
        Exercises.RUNNING,
        R.drawable.exercise_running_image,
        R.string.exercises_running_title,
        R.string.exercises_running_text
    ),
)