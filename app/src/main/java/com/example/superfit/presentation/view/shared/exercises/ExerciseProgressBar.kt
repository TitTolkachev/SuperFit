package com.example.superfit.presentation.view.shared.exercises

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.theme.Pink

@Composable
fun CircularProgressAnimated() {
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900))
    )

    CircularProgressIndicator(
        progress = progressAnimationValue,
        modifier = Modifier
            .padding(horizontal = 72.dp)
            .aspectRatio(1f)
            .fillMaxWidth(),
        color = Pink,
        strokeWidth = 4.dp
    )
}