package com.example.superfit.presentation.view.screens.body.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.WhiteC6
import com.example.superfit.presentation.theme.montserratFamily

@Composable
fun TrainProgressButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 32.dp, start = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = onClick,
            contentPadding = PaddingValues(8.dp)
        ) {
            TrainProgressText()
            Image(
                painter = painterResource(id = R.drawable.white_right_arrow),
                contentDescription = null
            )
        }
    }
}

@Composable
fun StatisticsButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 16.dp, start = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = onClick,
            contentPadding = PaddingValues(8.dp)
        ) {
            StatisticsText()
            Image(
                painter = painterResource(id = R.drawable.white_right_arrow),
                contentDescription = null
            )
        }
    }
}

@Composable
fun EditButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = onClick,
            contentPadding = PaddingValues(0.dp)
        ) {
            EditButtonText()
        }
    }
}

@Composable
fun SeeAllButton(onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .padding(end = 16.dp)
            .offset(y = 12.dp), onClick = onClick
    ) {
        Text(
            text = LocalContext.current.getString(R.string.body_see_all_text),
            fontSize = 12.sp,
            color = WhiteC6,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.W400,
            maxLines = 1
        )
    }
}