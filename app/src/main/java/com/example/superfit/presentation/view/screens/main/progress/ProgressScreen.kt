package com.example.superfit.presentation.view.screens.main.progress

import android.content.Context.DISPLAY_SERVICE
import android.content.Context.WINDOW_SERVICE
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.os.Build
import android.view.Display
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.theme.Pink
import com.example.superfit.presentation.view.screens.main.progress.components.DrawExercise
import com.example.superfit.presentation.view.screens.main.progress.components.ProgressTopBackButton
import com.example.superfit.presentation.view.screens.main.progress.components.drawLines

@Composable
fun ProgressScreen(navController: NavController, viewModel: ProgressViewModel = hiltViewModel()) {

    val state = viewModel.state

    LaunchedEffect(key1 = state.navigateBack) {
        if (state.navigateBack == true) {
            viewModel.accept(ProgressScreenIntent.Navigated)
            navController.popBackStack()
        }
    }

    ProgressScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun ProgressScreenContent(
    state: ProgressScreenState,
    sendEvent: (ProgressScreenIntent) -> Unit
) {
    Box {

        // Background
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.image_train_progress_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        // One Percent of display size in pixels for lines offset
        val windowManager = LocalContext.current.getSystemService(WINDOW_SERVICE) as WindowManager
        val displayManager =
            LocalContext.current.getSystemService(DISPLAY_SERVICE) as DisplayManager
        val displaySize = getDisplaySize(windowManager, displayManager)
        val onePercentOfDisplayHeightInPixels = with(LocalDensity.current) {
            displaySize.first / 100f
        }
        val onePercentOfDisplayWidthInPixels = with(LocalDensity.current) {
            displaySize.second / 100f
        }

        // Draw Lines for Exercises
        Box(modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawLines(
                    drawScope = this,
                    color = Pink,
                    onePercentHeight = onePercentOfDisplayHeightInPixels,
                    onePercentWidth = onePercentOfDisplayWidthInPixels,
                )
            })

        // Draw Text for Exercises
        state.exercises.forEach {
            DrawExercise(it)
        }

        // Top Button
        ProgressTopBackButton(onClick = { sendEvent(ProgressScreenIntent.NavigateBack) })
    }
}

private fun getDisplaySize(
    windowManager: WindowManager,
    displayManager: DisplayManager
): Pair<Int, Int> {
    val deviceHeight: Int
    val deviceWidth: Int

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val wMetrics = windowManager.currentWindowMetrics
        val bounds = wMetrics.bounds
        deviceHeight = bounds.height()
        deviceWidth = bounds.width()
    } else {
        val displayInfo =
            displayManager.getDisplay(
                Display.DEFAULT_DISPLAY
            )

        val realSize = Point()
        Display::class.java.getMethod("getRealSize", Point::class.java)
            .invoke(displayInfo, realSize)
        displayInfo.getRealSize(realSize)
        deviceWidth = realSize.x
        deviceHeight = realSize.y
    }

    return Pair(deviceHeight, deviceWidth)
}