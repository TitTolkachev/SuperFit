package com.example.superfit.presentation.view.screens.body.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R

@Composable
fun ProgressPhotosCard(onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .padding(top = 14.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(216.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 2.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.main_screen_poster),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 2.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.main_screen_poster),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                    painter = painterResource(id = R.drawable.body_add_image_icon),
                    contentDescription = null
                )
            }
        }
    }
}