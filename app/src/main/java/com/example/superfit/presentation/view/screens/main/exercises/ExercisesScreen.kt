package com.example.superfit.presentation.view.screens.main.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.model.Exercises
import com.example.superfit.presentation.view.screens.main.exercises.components.ExercisesText
import com.example.superfit.presentation.view.screens.main.main.components.ExerciseCard
import com.example.superfit.presentation.view.screens.main.main.components.Poster

@Preview
@Composable
fun DefaultPreview() {
    ExercisesScreenContent(ExercisesScreenState()) {}
}

@Composable
fun ExercisesScreen(navController: NavController, viewModel: ExercisesViewModel = hiltViewModel()) {

    val state = viewModel.state

    LaunchedEffect(key1 = viewModel.state.navigateBack) {
        if (viewModel.state.navigateBack == true) {
            navController.popBackStack()
        }
    }

    LaunchedEffect(key1 = state.showExercise) {
        when (state.showExercise) {
            Exercises.PUSH_UPS -> {
                navController.navigate(Screen.PushUps.route)
                viewModel.accept(ExercisesScreenIntent.Navigated)
            }

            Exercises.PLANK -> {
                navController.navigate(Screen.Plank.route)
                viewModel.accept(ExercisesScreenIntent.Navigated)
            }

            Exercises.SQUATS -> {
                navController.navigate(Screen.Squats.route)
                viewModel.accept(ExercisesScreenIntent.Navigated)
            }

            Exercises.CRUNCH -> {
                navController.navigate(Screen.Crunch.route)
                viewModel.accept(ExercisesScreenIntent.Navigated)
            }

            Exercises.RUNNING -> {
                navController.navigate(Screen.Running.route)
                viewModel.accept(ExercisesScreenIntent.Navigated)
            }

            else -> {}
        }
    }

    ExercisesScreenContent(state) { event: ExercisesScreenIntent -> viewModel.accept(event) }
}

@Composable
fun ExercisesScreenContent(
    state: ExercisesScreenState,
    sendEvent: (ExercisesScreenIntent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Box {
                Poster()
                Image(
                    painter = painterResource(id = R.drawable.white_back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 60.dp, start = 16.dp)
                        .clickable { sendEvent(ExercisesScreenIntent.NavigateBack) }
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                ExercisesText()
            }
        }
        items(state.exercises.size) {
            ExerciseCard(state.exercises[it]) { exercise ->
                sendEvent(
                    ExercisesScreenIntent.ShowExerciseScreen(exercise)
                )
            }
        }
    }
}