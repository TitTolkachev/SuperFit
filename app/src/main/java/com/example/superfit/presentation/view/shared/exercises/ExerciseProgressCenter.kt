package com.example.superfit.presentation.view.shared.exercises

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.superfit.R

@Composable
fun ExerciseProgressCenter(
    repeatsCount: Int?
) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ExerciseRepeatsCountText(repeatsCount?.toString() ?: "")
            ExerciseRepeatsLeftText(LocalContext.current.getString(R.string.exercises_need_to_do_text))
        }
        CircularProgressAnimated()
    }
}