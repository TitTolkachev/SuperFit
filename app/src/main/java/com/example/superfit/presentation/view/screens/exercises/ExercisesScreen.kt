package com.example.superfit.presentation.view.screens.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.view.model.Exercise
import com.example.superfit.presentation.view.screens.exercises.components.ExercisesText
import com.example.superfit.presentation.view.screens.main.components.ExerciseCard
import com.example.superfit.presentation.view.screens.main.components.Poster

@Preview
@Composable
fun DefaultPreview() {
    ExercisesScreenContent()
}

@Composable
fun ExercisesScreen(navController: NavController, viewModel: ExercisesViewModel = hiltViewModel()) {

    ExercisesScreenContent()
}

@Composable
fun ExercisesScreenContent() {

    //TODO()
    val exercisesFromState = remember {
        mutableListOf(
            Exercise(
                R.drawable.exercise_push_ups_image,
                R.string.exercises_push_ups_title,
                R.string.exercises_push_ups_text
            ),
            Exercise(
                R.drawable.exercise_plank_image,
                R.string.exercises_plank_title,
                R.string.exercises_plank_text
            ),
            Exercise(
                R.drawable.exercise_squats_image,
                R.string.exercises_squats_title,
                R.string.exercises_squats_text
            ),
            Exercise(
                R.drawable.exercise_crunch_image,
                R.string.exercises_crunch_title,
                R.string.exercises_crunch_text
            ),
            Exercise(
                R.drawable.exercise_running_image,
                R.string.exercises_running_title,
                R.string.exercises_running_text
            ),
        )
    }

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
//                    .clickable { sendEvent(SignInScreenUiEvent.PrevPage) }
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
        items(exercisesFromState.size) {
            ExerciseCard(exercisesFromState[it]) {}
        }
    }
}