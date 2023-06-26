package com.example.superfit.presentation.view.screens.exercises.crunch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.shared.exercises.ExerciseFinishButton
import com.example.superfit.presentation.view.shared.exercises.ExerciseProgressCenter
import com.example.superfit.presentation.view.shared.exercises.ExerciseTitle

@Composable
fun CrunchScreen(navController: NavController, viewModel: CrunchViewModel = hiltViewModel()) {

    val state = viewModel.state

    LaunchedEffect(key1 = state.navigateToSuccessScreen) {
        if (state.navigateToSuccessScreen == true) {
            viewModel.accept(CrunchScreenIntent.Navigated)
            navController.popBackStack()
            navController.navigate(Screen.Success.route)
        }
    }

    CrunchScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun CrunchScreenContent(
    state: CrunchScreenState,
    sendEvent: (CrunchScreenIntent) -> Unit
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
            ExerciseTitle(LocalContext.current.getString(R.string.exercises_crunch_title))
            ExerciseProgressCenter(state.repeatsCount)
        }
        ExerciseFinishButton {
            sendEvent(CrunchScreenIntent.Finish)
        }
    }
}