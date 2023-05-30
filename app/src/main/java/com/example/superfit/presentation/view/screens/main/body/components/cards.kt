package com.example.superfit.presentation.view.screens.main.body.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    .fillMaxSize()
                    .padding(end = 2.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.main_screen_poster),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                ImageDateText("21.04.2019")
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize()
                    .padding(start = 2.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.main_screen_poster),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ImageDateText(text = "22.02.2020")
                    Image(
                        modifier = Modifier
                            .padding(bottom = 8.dp, end = 8.dp)
                            .clickable(onClick = onClick),
                        painter = painterResource(id = R.drawable.body_add_image_icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}