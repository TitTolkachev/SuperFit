package com.example.superfit.presentation.view.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
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
    MainScreenContent()
}

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    MainScreenContent()
}

@Composable
fun MainScreenContent() {

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
            MyBodyCard {}
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                LastExercisesText()
                SeeAllButton {}
            }
            LastExercises()
        }
        item {
            SignOut {}
        }
    }
}

@Composable
private fun LastExercises() {
    ExerciseCard(
        R.drawable.main_screen_push_ups_image,
        R.string.main_push_ups_title,
        R.string.main_push_ups_text
    ) {}
    ExerciseCard(
        R.drawable.main_screen_plank_image,
        R.string.main_plank_title,
        R.string.main_plank_text
    ) {}
}