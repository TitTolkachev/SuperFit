package com.example.superfit.presentation.view.screens.main.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.view.screens.main.progress.components.DrawExercise
import com.example.superfit.presentation.view.screens.main.progress.components.ProgressTopBackButton

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

        // Draw Lines and Text for Exercises
        state.exercises.forEach {
            DrawExercise(it)
        }

        // Top Button
        ProgressTopBackButton(onClick = { sendEvent(ProgressScreenIntent.NavigateBack) })
    }
}