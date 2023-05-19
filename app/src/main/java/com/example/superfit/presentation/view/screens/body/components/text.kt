package com.example.superfit.presentation.view.screens.body.components

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
        text = LocalContext.current.getString(R.string.body_my_body_text),
        modifier = Modifier
            .padding(start = 16.dp, top = 48.dp),
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}

@Composable
fun MyProgressText() {
    Text(
        text = LocalContext.current.getString(R.string.body_my_progress_text),
        modifier = Modifier
            .padding(start = 16.dp, top = 48.dp),
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}

@Composable
fun TrainProgressText() {
    Text(
        text = LocalContext.current.getString(R.string.body_train_progress_text),
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}

@Composable
fun StatisticsText() {
    Text(
        text = LocalContext.current.getString(R.string.body_statistics_text),
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}