package com.example.superfit.presentation.view.screens.main.body.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.Pink
import com.example.superfit.presentation.theme.WhiteC6
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
fun MyWeightText(value: Int?) {
    Text(
        text = value?.let { it.toString() + LocalContext.current.getString(R.string.body_my_body_weight_text) }
            ?: stringResource(id = R.string.body_params_default_text),
        fontSize = 36.sp,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun MyHeightText(value: Int?) {
    Text(
        text = value?.let { it.toString() + LocalContext.current.getString(R.string.body_my_body_height_text) }
            ?: stringResource(id = R.string.body_params_default_text),
        fontSize = 36.sp,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun EditButtonText() {
    Text(
        text = LocalContext.current.getString(R.string.body_edit_text),
        fontSize = 12.sp,
        textAlign = TextAlign.Start,
        color = WhiteC6,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.W400,
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
fun ImageDateText(text: String) {
    Text(
        modifier = Modifier
            .padding(start = 8.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Pink)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = text,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.W400,
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