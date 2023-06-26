package com.example.superfit.presentation.view.shared.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R


@Preview
@Composable
fun ExerciseProgressCenter() {
    ExerciseProgressCenter(null, null, null)
}

@Composable
fun ExerciseProgressCenter(
    repeatsTotal: Float?,
    repeatsLeft: Float?,
    repeatsLeftText: String?
) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (repeatsLeftText == null)
                Image(
                    painter = painterResource(id = R.drawable.exercise_tick),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 122.dp, end = 117.dp)
                )
            else {
                ExerciseRepeatsCountText(repeatsLeft?.toInt()?.toString() ?: "")
                ExerciseRepeatsLeftText(repeatsLeftText)
            }
        }

        if (repeatsLeft != null && repeatsTotal != null && repeatsLeftText != null) {
            CircularProgressAnimated(repeatsLeft / repeatsTotal)
        } else
            CircularProgressAnimated(1f)
    }
}