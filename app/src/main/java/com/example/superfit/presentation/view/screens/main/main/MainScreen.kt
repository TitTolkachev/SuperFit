package com.example.superfit.presentation.view.screens.main.main

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
import com.example.superfit.presentation.view.model.EXERCISES
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.model.Exercises
import com.example.superfit.presentation.view.screens.main.main.components.ExerciseCard
import com.example.superfit.presentation.view.screens.main.main.components.LastExercisesText
import com.example.superfit.presentation.view.screens.main.main.components.MyBodyCard
import com.example.superfit.presentation.view.screens.main.main.components.MyBodyText
import com.example.superfit.presentation.view.screens.main.main.components.Poster
import com.example.superfit.presentation.view.screens.main.main.components.SeeAllButton
import com.example.superfit.presentation.view.screens.main.main.components.SignOut

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
            viewModel.accept(MainScreenIntent.Navigated)
        }
    }

    LaunchedEffect(key1 = state.signOut) {
        if (state.signOut == true) {
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.Main.route) {
                    inclusive = true
                }
            }
            viewModel.accept(MainScreenIntent.Navigated)
        }
    }

    LaunchedEffect(key1 = state.showBodyScreen) {
        if (state.showBodyScreen == true) {
            navController.navigate(Screen.Body.route)
            viewModel.accept(MainScreenIntent.Navigated)
        }
    }

    LaunchedEffect(key1 = state.showExercise) {
//        TODO
//        when (state.showExercise) {
//            Exercises.PUSH_UPS -> {
//                navController.navigate(Screen.SignIn.route)
//                viewModel.accept(MainScreenIntent.Navigated)
//            }
//
//            Exercises.PLANK -> {
//                navController.navigate(Screen.SignIn.route)
//                viewModel.accept(MainScreenIntent.Navigated)
//            }
//
//            Exercises.SQUATS -> {
//                navController.navigate(Screen.SignIn.route)
//                viewModel.accept(MainScreenIntent.Navigated)
//            }
//
//            Exercises.CRUNCH -> {
//                navController.navigate(Screen.SignIn.route)
//                viewModel.accept(MainScreenIntent.Navigated)
//            }
//
//            Exercises.RUNNING -> {
//                navController.navigate(Screen.SignIn.route)
//                viewModel.accept(MainScreenIntent.Navigated)
//            }
//
//            else -> {}
//        }
    }

    MainScreenContent(state) { event: MainScreenIntent -> viewModel.accept(event) }
}

@Composable
fun MainScreenContent(
    state: MainScreenState,
    sendEvent: (MainScreenIntent) -> Unit
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
                sendEvent(MainScreenIntent.ShowBodyScreen)
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                LastExercisesText()
                SeeAllButton { sendEvent(MainScreenIntent.ShowAllExercisesScreen) }
            }
            LastExercises(state.lastExercises, sendEvent)
        }
        item {
            SignOut { sendEvent(MainScreenIntent.SignOut) }
        }
    }
}

@Composable
private fun LastExercises(
    lastExercises: List<Exercise>?,
    sendEvent: (MainScreenIntent) -> Unit
) {
    if (lastExercises.isNullOrEmpty()) {
        ExerciseCard(EXERCISES[Exercises.PUSH_UPS.ordinal]) { exercise ->
            sendEvent(
                MainScreenIntent.ShowExerciseScreen(exercise)
            )
        }
        ExerciseCard(EXERCISES[Exercises.PLANK.ordinal]) { exercise ->
            sendEvent(
                MainScreenIntent.ShowExerciseScreen(exercise)
            )
        }
    } else if (lastExercises.size == 1) {
        if (lastExercises[0].exercise == Exercises.PUSH_UPS) {
            ExerciseCard(lastExercises[0]) { exercise ->
                sendEvent(
                    MainScreenIntent.ShowExerciseScreen(exercise)
                )
            }
            ExerciseCard(EXERCISES[Exercises.PLANK.ordinal]) { exercise ->
                sendEvent(
                    MainScreenIntent.ShowExerciseScreen(exercise)
                )
            }
        } else {
            ExerciseCard(lastExercises[0]) { exercise ->
                sendEvent(
                    MainScreenIntent.ShowExerciseScreen(exercise)
                )
            }
            ExerciseCard(EXERCISES[Exercises.PUSH_UPS.ordinal]) { exercise ->
                sendEvent(
                    MainScreenIntent.ShowExerciseScreen(exercise)
                )
            }
        }
    } else {
        ExerciseCard(lastExercises[0]) { exercise ->
            sendEvent(
                MainScreenIntent.ShowExerciseScreen(exercise)
            )
        }
        ExerciseCard(lastExercises[1]) { exercise ->
            sendEvent(
                MainScreenIntent.ShowExerciseScreen(exercise)
            )
        }
    }
}