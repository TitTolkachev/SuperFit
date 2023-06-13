package com.example.superfit.presentation.view.screens.main.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.smarttoolfactory.image.zoom.ZoomableImage

@Composable
fun ImageScreen(navController: NavController, viewModel: ImageViewModel = hiltViewModel()) {

    val state = viewModel.state

    ImageScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun ImageScreenContent(
    state: ImageScreenState,
    sendEvent: (ImageScreenIntent) -> Unit
) {

    ZoomableImage(
        modifier = Modifier.fillMaxSize(),
        imageBitmap = ImageBitmap.imageResource(id = R.drawable.exercise_running_image)
    )
}