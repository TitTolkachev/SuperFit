package com.example.superfit.presentation.view.screens.main.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.DarkBackground
import com.example.superfit.presentation.theme.DarkGray28
import com.example.superfit.presentation.theme.DarkGray28Transparent
import com.example.superfit.presentation.theme.WhiteC6
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.model.Exercises

@Composable
fun Poster() {

    Box(contentAlignment = Alignment.Center) {

        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(id = R.drawable.main_screen_poster),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White),
            )
        }

        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
fun MyBodyCard(
    weight: String,
    height: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(114.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        Row(Modifier.background(DarkBackground)) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_screen_body_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .width(24.dp)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkGray28Transparent,
                                    DarkGray28
                                )
                            )
                        )
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.main_body_weight_icon),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = weight,
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily = montserratFamily,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.main_body_height_icon),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = height,
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily = montserratFamily,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                DetailsButton() //{}// (onClick)
            }
        }
    }
}

@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: (Exercises) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(114.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onClick(exercise.exercise) }),
    ) {
        Row(
            Modifier
                .background(DarkBackground)
                .fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = exercise.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .width(24.dp)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkGray28Transparent,
                                    DarkGray28
                                )
                            )
                        )
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    text = stringResource(exercise.title),
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 8.dp),
                    text = stringResource(exercise.text),
                    fontSize = 12.sp,
                    color = WhiteC6,
                    lineHeight = 15.sp,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.W400,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5
                )
            }
        }
    }
}
