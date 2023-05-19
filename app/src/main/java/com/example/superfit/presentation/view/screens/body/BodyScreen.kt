package com.example.superfit.presentation.view.screens.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.view.screens.body.components.MyBodyText
import com.example.superfit.presentation.view.screens.body.components.MyProgressText
import com.example.superfit.presentation.view.screens.body.components.StatisticsButton
import com.example.superfit.presentation.view.screens.body.components.TrainProgressButton

@Composable
fun BodyScreen(navController: NavController, viewModel: BodyViewModel = hiltViewModel()) {

    val state = viewModel.state

    BodyScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun BodyScreenContent(
    state: BodyScreenState,
    sendEvent: (BodyScreenUiEvent) -> Unit
) {
    Column {
        MyBodyText()
        Row {

        }
        MyProgressText()
        TrainProgressButton {}
        StatisticsButton {}
    }
}