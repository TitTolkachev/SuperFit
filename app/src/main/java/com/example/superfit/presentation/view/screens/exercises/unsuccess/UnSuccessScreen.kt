package com.example.superfit.presentation.view.screens.exercises.unsuccess

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.shared.exercises.CircularProgressAnimated
import com.example.superfit.presentation.view.shared.exercises.ExerciseGoHomeButton
import com.example.superfit.presentation.view.shared.exercises.ExerciseTitle

@Composable
fun UnSuccessScreen(navController: NavController, title: String, repeats: Int) {

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
            UnSuccessProgressCenter(
                repeats,
                stringResource(id = R.string.exercises_un_success_text)
            )
        }
        ExerciseGoHomeButton {
            navController.popBackStack()
        }
    }
}

@Composable
private fun UnSuccessProgressCenter(
    repeatsLeft: Int,
    repeatsLeftText: String
) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            UnSuccessRepeatsLeftText(repeatsLeft.toString() + repeatsLeftText)
        }

        CircularProgressAnimated(1f)
    }
}

@Composable
fun UnSuccessRepeatsLeftText(
    text: String
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFamily,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 110.dp)
            .fillMaxWidth(),
    )
}