package com.example.superfit.presentation.view.screens.main.exercises.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily

@Composable
fun ExercisesText() {
    Text(
        text = stringResource(R.string.exercises_exercises_text),
        modifier = Modifier
            .padding(start = 16.dp, top = 24.dp),
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
    )
}