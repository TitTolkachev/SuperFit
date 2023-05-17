package com.example.superfit.presentation.view.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.screens.main.components.ExerciseCard
import com.example.superfit.presentation.view.screens.main.components.LastExercisesText
import com.example.superfit.presentation.view.screens.main.components.MyBodyCard
import com.example.superfit.presentation.view.screens.main.components.MyBodyText
import com.example.superfit.presentation.view.screens.main.components.Poster
import com.example.superfit.presentation.view.screens.main.components.SeeAllButton
import com.example.superfit.presentation.view.screens.main.components.SignOut

@Preview
@Composable
fun DefaultPreview() {
    MainScreenContent(MainScreenState()) {}
}

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    val state = viewModel.state

    LaunchedEffect(key1 = state.showAllExercises) {
        if (state.showAllExercises == true) {
            navController.navigate(Screen.Exercises.route)
            viewModel.accept(MainScreenUiEvent.Navigated)
        }
    }

    LaunchedEffect(key1 = state.signOut) {
        if (state.signOut == true) {
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.Main.route) {
                    inclusive = true
                }
            }
            viewModel.accept(MainScreenUiEvent.Navigated)
        }
    }

    MainScreenContent(state) { event: MainScreenUiEvent -> viewModel.accept(event) }
}

@Composable
fun MainScreenContent(
    state: MainScreenState,
    sendEvent: (MainScreenUiEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        item {
            Poster()
        }
        item {
            MyBodyText()
            MyBodyCard(
                weight = (state.bodyWeight
                    ?: LocalContext.current.getString(R.string.main_default_body_value)).toString(),
                height = (state.bodyHeight
                    ?: LocalContext.current.getString(R.string.main_default_body_value)).toString()
            ) {
                sendEvent(MainScreenUiEvent.ShowBodyScreen)
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                LastExercisesText()
                SeeAllButton { sendEvent(MainScreenUiEvent.ShowAllExercisesScreen) }
            }
            LastExercises()
        }
        item {
            SignOut { sendEvent(MainScreenUiEvent.SignOut) }
        }
    }
}

@Composable
private fun LastExercises() {
    ExerciseCard(
        Exercise(
            R.drawable.exercise_push_ups_image,
            R.string.exercises_push_ups_title,
            R.string.exercises_push_ups_text
        )
    ) {}
    ExerciseCard(
        Exercise(
            R.drawable.exercise_plank_image,
            R.string.exercises_plank_title,
            R.string.exercises_plank_text
        )
    ) {}
}