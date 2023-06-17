package com.example.superfit.presentation.view.screens.main.body

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.model.Photo
import com.example.superfit.presentation.view.screens.main.body.components.EditButton
import com.example.superfit.presentation.view.screens.main.body.components.MyBodyText
import com.example.superfit.presentation.view.screens.main.body.components.MyHeightText
import com.example.superfit.presentation.view.screens.main.body.components.MyProgressText
import com.example.superfit.presentation.view.screens.main.body.components.MyWeightText
import com.example.superfit.presentation.view.screens.main.body.components.ProgressPhotosCard
import com.example.superfit.presentation.view.screens.main.body.components.SeeAllButton
import com.example.superfit.presentation.view.screens.main.body.components.StatisticsButton
import com.example.superfit.presentation.view.screens.main.body.components.TrainProgressButton
import com.example.superfit.presentation.view.screens.main.body.dialogs.BodyInputDialog
import com.example.superfit.presentation.view.screens.main.body.dialogs.NewImageDialog

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BodyScreen(navController: NavController, viewModel: BodyViewModel = hiltViewModel()) {

    val state = viewModel.state
    val inputDialogState = viewModel.inputDialogState

    LaunchedEffect(key1 = state.showImage) {
        if (state.showImage != null) {
            navController.navigate(Screen.Image.route + "/${state.showImage.id}/${state.showImage.date}")
            viewModel.accept(BodyScreenIntent.Navigated)
        }
    }

    LaunchedEffect(key1 = state.showImages) {
        if (state.showImages != null) {
            navController.navigate(Screen.ImageList.route)
            viewModel.accept(BodyScreenIntent.Navigated)
        }
    }

    if (state.takePicture == true) {
        NewImageDialog(state) { event -> viewModel.accept(event) }
    }

    if (inputDialogState.editWeight == true || inputDialogState.editHeight == true) {
        BodyInputDialog(
            state = inputDialogState,
            onChange = { text: String -> viewModel.accept(BodyInputDialogIntent.NewText(text)) },
            onSaveChanges = { viewModel.accept(BodyInputDialogIntent.SaveChanges) },
            onCloseDialog = { viewModel.accept(BodyInputDialogIntent.CloseDialog) })
    }

    BodyScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun BodyScreenContent(
    state: BodyScreenState,
    sendEvent: (BodyScreenIntent) -> Unit
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
                MyWeightText(state.weight)
                EditButton { sendEvent(BodyScreenIntent.EditWeight) }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MyHeightText(state.height)
                EditButton { sendEvent(BodyScreenIntent.EditHeight) }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            MyProgressText()
            SeeAllButton { sendEvent(BodyScreenIntent.ShowImages) }
        }
        ProgressPhotosCard(
            state.firstPhoto,
            state.lastPhoto,
            { image: Photo ->
                sendEvent(BodyScreenIntent.ShowImage(image))
            }) {
            sendEvent(BodyScreenIntent.TakePicture)
        }
        TrainProgressButton { sendEvent(BodyScreenIntent.ShowTrainProgress) }
        StatisticsButton { sendEvent(BodyScreenIntent.ShowStatistics) }
    }
}