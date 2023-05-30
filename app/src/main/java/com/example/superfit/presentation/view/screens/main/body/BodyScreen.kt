package com.example.superfit.presentation.view.screens.main.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.view.screens.main.body.components.EditButton
import com.example.superfit.presentation.view.screens.main.body.components.MyBodyText
import com.example.superfit.presentation.view.screens.main.body.components.MyHeightText
import com.example.superfit.presentation.view.screens.main.body.components.MyProgressText
import com.example.superfit.presentation.view.screens.main.body.components.MyWeightText
import com.example.superfit.presentation.view.screens.main.body.components.ProgressPhotosCard
import com.example.superfit.presentation.view.screens.main.body.components.SeeAllButton
import com.example.superfit.presentation.view.screens.main.body.components.StatisticsButton
import com.example.superfit.presentation.view.screens.main.body.components.TrainProgressButton

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MyWeightText(state.weight.toString())
                EditButton { sendEvent(BodyScreenUiEvent.EditWeight) }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MyHeightText(state.height.toString())
                EditButton { sendEvent(BodyScreenUiEvent.EditHeight) }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            MyProgressText()
            SeeAllButton { sendEvent(BodyScreenUiEvent.ShowImages) }
        }
        ProgressPhotosCard { sendEvent(BodyScreenUiEvent.TakePicture) }
        TrainProgressButton { sendEvent(BodyScreenUiEvent.ShowTrainProgress) }
        StatisticsButton { sendEvent(BodyScreenUiEvent.ShowStatistics) }
    }
}