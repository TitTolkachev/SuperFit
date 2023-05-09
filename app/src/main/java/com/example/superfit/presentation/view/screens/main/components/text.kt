package com.example.superfit.presentation.view.screens.main.components

import androidx.compose.foundation.layout.fillMaxWidth
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
fun MyBodyText() {
    Text(
        text = LocalContext.current.getString(R.string.main_my_body_text),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}

@Composable
fun LastExercisesText() {
    Text(
        text = LocalContext.current.getString(R.string.main_last_exercises_text),
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

@Composable
fun SignOutText() {
    Text(
        text = LocalContext.current.getString(R.string.main_sign_out_text),
        fontSize = 24.sp,
        color = Color.Black,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}