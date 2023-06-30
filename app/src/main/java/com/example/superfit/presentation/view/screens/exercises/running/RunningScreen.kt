package com.example.superfit.presentation.view.screens.exercises.running

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.shared.exercises.ExerciseFinishButton
import com.example.superfit.presentation.view.shared.exercises.ExerciseProgressCenter
import com.example.superfit.presentation.view.shared.exercises.ExerciseTitle


@Composable
fun RunningScreen(navController: NavController, viewModel: RunningViewModel = hiltViewModel()) {

    val state = viewModel.state

    val title = stringResource(id = R.string.exercises_running_title)
    LaunchedEffect(key1 = state.navigateToSuccessScreen) {
        if (state.navigateToSuccessScreen == true) {
            viewModel.accept(RunningScreenIntent.Navigated)
            navController.popBackStack()
            navController.navigate(Screen.Success.route + "/$title")
        }
    }

    LaunchedEffect(key1 = state.navigateToUnSuccessScreen) {
        if (state.navigateToUnSuccessScreen == true) {
            viewModel.accept(RunningScreenIntent.Navigated)
            navController.popBackStack()
            val repeatsLeft =
                if (state.totalRepeatsCount == null || state.repeatsCount == null) 0
                else state.totalRepeatsCount - state.repeatsCount
            navController.navigate(Screen.UnSuccess.route + "/$title/$repeatsLeft")
        }
    }

    RunningScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun RunningScreenContent(
    state: RunningScreenState,
    sendEvent: (RunningScreenIntent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExerciseTitle(stringResource(R.string.exercises_running_title))
            ExerciseProgressCenter(
                (state.repeatsCount?.toFloat() ?: 1f) / (state.totalRepeatsCount?.toFloat()
                    ?: 1f),
                if (state.totalRepeatsCount != null && state.repeatsCount != null)
                    (state.totalRepeatsCount - state.repeatsCount)
                else null,
                stringResource(R.string.exercises_meters_left_text)
            )
        }
        ExerciseFinishButton {
            sendEvent(RunningScreenIntent.Finish)
        }
    }
}