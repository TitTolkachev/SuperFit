package com.example.superfit.presentation.view.screens.body.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R

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