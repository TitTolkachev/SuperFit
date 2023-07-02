package com.example.superfit.presentation.view.screens.main.progress.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.model.ExerciseProgress
import com.example.superfit.presentation.view.model.Exercises

const val figmaHeight = 640f
const val figmaWidth = 360f

val circles = listOf(
    Pair(137f * 100 / figmaWidth, 155f * 100 / figmaHeight),
    Pair(129f * 100 / figmaWidth, 224f * 100 / figmaHeight),
    Pair(99f * 100 / figmaWidth, 268f * 100 / figmaHeight),
    Pair(127f * 100 / figmaWidth, 421f * 100 / figmaHeight),
    Pair(127f * 100 / figmaWidth, 593f * 100 / figmaHeight),
)

val lines = listOf(
    // Push-Ups
    Pair(
        Pair(139.5f * 100 / figmaWidth, 153f * 100 / figmaHeight),
        Pair(167f * 100 / figmaWidth, 126f * 100 / figmaHeight)
    ),
    Pair(
        Pair(166.5f * 100 / figmaWidth, 126f * 100 / figmaHeight),
        Pair(294.5f * 100 / figmaWidth, 126f * 100 / figmaHeight)
    ),
    // Plank
    Pair(
        Pair(132.5f * 100 / figmaWidth, 224f * 100 / figmaHeight),
        Pair(302f * 100 / figmaWidth, 224f * 100 / figmaHeight)
    ),
    // Crunch
    Pair(
        Pair(101f * 100 / figmaWidth, 271f * 100 / figmaHeight),
        Pair(153.5f * 100 / figmaWidth, 324f * 100 / figmaHeight)
    ),
    Pair(
        Pair(153f * 100 / figmaWidth, 323.5f * 100 / figmaHeight),
        Pair(295.5f * 100 / figmaWidth, 323.5f * 100 / figmaHeight)
    ),
    // Squats
    Pair(
        Pair(129.5f * 100 / figmaWidth, 424f * 100 / figmaHeight),
        Pair(159f * 100 / figmaWidth, 454f * 100 / figmaHeight)
    ),
    Pair(
        Pair(158.5f * 100 / figmaWidth, 453.5f * 100 / figmaHeight),
        Pair(292f * 100 / figmaWidth, 453.5f * 100 / figmaHeight)
    ),
    // Running
    Pair(
        Pair(130f * 100 / figmaWidth, 592f * 100 / figmaHeight),
        Pair(159f * 100 / figmaWidth, 563f * 100 / figmaHeight)
    ),
    Pair(
        Pair(158.5f * 100 / figmaWidth, 563.5f * 100 / figmaHeight),
        Pair(292f * 100 / figmaWidth, 563.5f * 100 / figmaHeight)
    )
)

// Offsets in percents
const val pushUpsX = 175f * 100 / figmaWidth
val pushUpsY = listOf(107f * 100 / figmaHeight, 128f * 100 / figmaHeight, 143f * 100 / figmaHeight)
const val plankX = 161f * 100 / figmaWidth
val plankY = listOf(205f * 100 / figmaHeight, 228f * 100 / figmaHeight, 243f * 100 / figmaHeight)
const val crunchX = 163f * 100 / figmaWidth
val crunchY = listOf(303f * 100 / figmaHeight, 326f * 100 / figmaHeight, 341f * 100 / figmaHeight)
const val squatsX = 167f * 100 / figmaWidth
val squatsY = listOf(433f * 100 / figmaHeight, 456f * 100 / figmaHeight, 471f * 100 / figmaHeight)
const val runningX = 167.5f * 100 / figmaWidth
val runningY = listOf(542f * 100 / figmaHeight, 565f * 100 / figmaHeight, 580f * 100 / figmaHeight)

@Composable
fun DrawExercise(
    exerciseProgress: ExerciseProgress,
    onePercentHeightDp: Dp,
    onePercentWidthDp: Dp
) {

    var title = 0
    var lastTrainText = 0
    var offsetX = 0.dp
    var offsetTitleY = 0.dp
    var offsetLastTrainY = 0.dp
    var offsetProgressY = 0.dp
    when (exerciseProgress.type) {
        Exercises.PUSH_UP -> {
            title = R.string.exercises_push_ups_title
            lastTrainText = R.string.progress_times
            offsetX = (pushUpsX * onePercentWidthDp.value).dp
            offsetTitleY = (pushUpsY[0] * onePercentHeightDp.value).dp
            offsetLastTrainY = (pushUpsY[1] * onePercentHeightDp.value).dp
            offsetProgressY = (pushUpsY[2] * onePercentHeightDp.value).dp
        }

        Exercises.PLANK -> {
            title = R.string.exercises_plank_title
            lastTrainText = R.string.progress_seconds
            offsetX = (plankX * onePercentWidthDp.value).dp
            offsetTitleY = (plankY[0] * onePercentHeightDp.value).dp
            offsetLastTrainY = (plankY[1] * onePercentHeightDp.value).dp
            offsetProgressY = (plankY[2] * onePercentHeightDp.value).dp
        }

        Exercises.CRUNCH -> {
            title = R.string.exercises_crunch_title
            lastTrainText = R.string.progress_times
            offsetX = (crunchX * onePercentWidthDp.value).dp
            offsetTitleY = (crunchY[0] * onePercentHeightDp.value).dp
            offsetLastTrainY = (crunchY[1] * onePercentHeightDp.value).dp
            offsetProgressY = (crunchY[2] * onePercentHeightDp.value).dp
        }

        Exercises.SQUATS -> {
            title = R.string.exercises_squats_title
            lastTrainText = R.string.progress_times
            offsetX = (squatsX * onePercentWidthDp.value).dp
            offsetTitleY = (squatsY[0] * onePercentHeightDp.value).dp
            offsetLastTrainY = (squatsY[1] * onePercentHeightDp.value).dp
            offsetProgressY = (squatsY[2] * onePercentHeightDp.value).dp
        }

        Exercises.RUNNING -> {
            title = R.string.exercises_running_title
            lastTrainText = R.string.progress_meters
            offsetX = (runningX * onePercentWidthDp.value).dp
            offsetTitleY = (runningY[0] * onePercentHeightDp.value).dp
            offsetLastTrainY = (runningY[1] * onePercentHeightDp.value).dp
            offsetProgressY = (runningY[2] * onePercentHeightDp.value).dp
        }
    }

    // Exercise Title
    Text(
        text = stringResource(id = title),
        fontFamily = montserratFamily,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.offset(
            x = offsetX,
            y = offsetTitleY
        )
    )

    if (exerciseProgress.lastTrainRepeats != null) {

        // Last Train
        Row(
            modifier = Modifier.offset(
                x = offsetX,
                y = offsetLastTrainY
            )
        ) {
            Text(
                text = stringResource(id = R.string.progress_last_train),
                fontFamily = montserratFamily,
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                text = exerciseProgress.lastTrainRepeats.toString() + stringResource(id = lastTrainText),
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 12.sp,
            )
        }

        if (exerciseProgress.progress != null) {

            // Progress
            Row(
                modifier = Modifier.offset(
                    x = offsetX,
                    y = offsetProgressY,
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.progress_progress),
                    fontFamily = montserratFamily,
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    text = exerciseProgress.progress.toString() + '%',
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 12.sp
                )
                if (exerciseProgress.progress != 0) {
                    Image(
                        modifier = Modifier.padding(start = 4.dp),
                        painter = painterResource(
                            id = if (exerciseProgress.progress >= 0)
                                R.drawable.progress_green_arrow
                            else
                                R.drawable.progress_red_arrow
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


fun drawLines(drawScope: DrawScope, color: Color, onePercentHeight: Float, onePercentWidth: Float) {
    drawScope.apply {
        circles.forEach {
            drawCircle(
                color,
                4.dp.toPx(),
                center = Offset(it.first * onePercentWidth, it.second * onePercentHeight),
                style = Stroke(width = 2.dp.toPx())
            )
        }

        lines.forEach {
            drawLine(
                color,
                Offset(it.first.first * onePercentWidth, it.first.second * onePercentHeight),
                Offset(it.second.first * onePercentWidth, it.second.second * onePercentHeight),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}