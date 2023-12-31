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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.view.model.Photo

@Composable
fun ProgressPhotosCard(
    firstPhoto: Photo?,
    lastPhoto: Photo?,
    onImageClick: (Photo) -> Unit,
    onClick: () -> Unit
) {
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
                if (firstPhoto != null) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onImageClick(firstPhoto) },
                        bitmap = firstPhoto.bitmap.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    ImageDateText(firstPhoto.date)
                } else {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.image_weak_man_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize()
                    .padding(start = 2.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                if (lastPhoto != null) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onImageClick(lastPhoto) },
                        bitmap = lastPhoto.bitmap.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ImageDateText(text = lastPhoto.date)
                        Image(
                            modifier = Modifier
                                .padding(bottom = 8.dp, end = 8.dp)
                                .clickable(onClick = onClick),
                            painter = painterResource(id = R.drawable.body_add_image_icon),
                            contentDescription = null
                        )
                    }
                } else {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.image_dialog_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box {}
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
}