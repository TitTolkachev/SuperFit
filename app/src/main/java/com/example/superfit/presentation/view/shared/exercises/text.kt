package com.example.superfit.presentation.view.shared.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily

@Composable
fun ExerciseTitle(
    text: String
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFamily,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 64.dp)
    )
}

@Composable
fun ExerciseFinishButtonText() {
    Text(
        text = LocalContext.current.getString(R.string.exercises_finish_button_text),
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFamily,
        maxLines = 1,
        lineHeight = 56.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ExerciseGoHomeButtonText() {
    Text(
        text = LocalContext.current.getString(R.string.exercises_go_home_button_text),
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFamily,
        maxLines = 1,
        lineHeight = 56.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ExerciseRepeatsLeftText(
    text: String
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFamily,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ExerciseRepeatsCountText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 64.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFamily,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}