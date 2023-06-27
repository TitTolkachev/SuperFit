package com.example.superfit.presentation.view.screens.exercises.squats

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.shared.exercises.ExerciseProgressCenter
import com.example.superfit.presentation.view.shared.exercises.ExerciseTitle

@Composable
fun SquatsScreen(navController: NavController, viewModel: SquatsViewModel = hiltViewModel()) {

    val state = viewModel.state

    val title = stringResource(id = R.string.exercises_squats_title)
    LaunchedEffect(key1 = state.navigateToSuccessScreen) {
        if (state.navigateToSuccessScreen == true) {
            viewModel.accept(SquatsScreenIntent.Navigated)
            navController.popBackStack()
            navController.navigate(Screen.Success.route + "/$title")
        }
    }

    LaunchedEffect(key1 = state.navigateBack) {
        if (state.navigateBack == true) {
            viewModel.accept(SquatsScreenIntent.Navigated)
            navController.popBackStack()
        }
    }

    SquatsScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun SquatsScreenContent(
    state: SquatsScreenState,
    sendEvent: (SquatsScreenIntent) -> Unit
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.white_back_arrow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 60.dp, start = 16.dp)
                .clickable { sendEvent(SquatsScreenIntent.NavigateBack) }
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ExerciseTitle(stringResource(R.string.exercises_squats_title))
                ExerciseProgressCenter(
                    (state.repeatsCount?.toFloat() ?: 1f) / (state.totalRepeatsCount?.toFloat()
                        ?: 1f),
                    if (state.totalRepeatsCount != null && state.repeatsCount != null)
                        (state.totalRepeatsCount - state.repeatsCount)
                    else null,
                    stringResource(R.string.exercises_times_left_text)
                )
            }
            Box { }
        }
    }
}