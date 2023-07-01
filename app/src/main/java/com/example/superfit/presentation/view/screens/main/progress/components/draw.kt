package com.example.superfit.presentation.view.screens.main.progress.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.view.model.ExerciseProgress

const val figmaHeight = 640f
const val figmaWidth = 360f

val circles = listOf(
    Pair((137f) * 100 / figmaWidth, 155f * 100 / figmaHeight),
    Pair(129f * 100 / figmaWidth, 224f * 100 / figmaHeight),
    Pair(99f * 100 / figmaWidth, 268f * 100 / figmaHeight),
    Pair(127f * 100 / figmaWidth, 421f * 100 / figmaHeight),
    Pair(127f * 100 / figmaWidth, 593f * 100 / figmaHeight),
)

val lines = listOf(
    Pair(
        Pair(139.5f * 100 / figmaWidth, 153f * 100 / figmaHeight),
        Pair(167f * 100 / figmaWidth, 126f * 100 / figmaHeight)
    ),
    Pair(
        Pair(166.5f * 100 / figmaWidth, 126f * 100 / figmaHeight),
        Pair(294.5f * 100 / figmaWidth, 126f * 100 / figmaHeight)
    ),
    Pair(
        Pair(132.5f * 100 / figmaWidth, 224f * 100 / figmaHeight),
        Pair(302f * 100 / figmaWidth, 224f * 100 / figmaHeight)
    ),
    Pair(
        Pair(101f * 100 / figmaWidth, 271f * 100 / figmaHeight),
        Pair(153.5f * 100 / figmaWidth, 324f * 100 / figmaHeight)
    ),
    Pair(
        Pair(153f * 100 / figmaWidth, 323.5f * 100 / figmaHeight),
        Pair(295.5f * 100 / figmaWidth, 323.5f * 100 / figmaHeight)
    ),
    Pair(
        Pair(129.5f * 100 / figmaWidth, 424f * 100 / figmaHeight),
        Pair(159f * 100 / figmaWidth, 454f * 100 / figmaHeight)
    ),
    Pair(
        Pair(158.5f * 100 / figmaWidth, 453.5f * 100 / figmaHeight),
        Pair(292f * 100 / figmaWidth, 453.5f * 100 / figmaHeight)
    ),
    Pair(
        Pair(130f * 100 / figmaWidth, 592f * 100 / figmaHeight),
        Pair(159f * 100 / figmaWidth, 563f * 100 / figmaHeight)
    ),
    Pair(
        Pair(158.5f * 100 / figmaWidth, 563.5f * 100 / figmaHeight),
        Pair(292f * 100 / figmaWidth, 563.5f * 100 / figmaHeight)
    )
)

@Composable
fun DrawExercise(exerciseProgress: ExerciseProgress) {


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