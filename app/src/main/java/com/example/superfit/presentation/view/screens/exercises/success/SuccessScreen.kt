package com.example.superfit.presentation.view.screens.exercises.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.presentation.view.shared.exercises.ExerciseGoHomeButton
import com.example.superfit.presentation.view.shared.exercises.ExerciseProgressCenter
import com.example.superfit.presentation.view.shared.exercises.ExerciseTitle

@Composable
fun SuccessScreen(navController: NavController, title: String){

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExerciseTitle(title)
            ExerciseProgressCenter(null, null, null)
        }
        ExerciseGoHomeButton {
            navController.popBackStack()
        }
    }
}