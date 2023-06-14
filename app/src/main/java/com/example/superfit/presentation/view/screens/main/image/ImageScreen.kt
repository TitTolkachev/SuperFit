package com.example.superfit.presentation.view.screens.main.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.view.screens.main.body.components.ImageDateText
import com.smarttoolfactory.image.zoom.ZoomableImage

@Composable
fun ImageScreen(
    navController: NavController,
    imageId: String,
    imageDate: String,
    viewModel: ImageViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LaunchedEffect(key1 = true) {
        viewModel.accept(ImageScreenIntent.LoadImage(imageId, imageDate))
    }

    LaunchedEffect(key1 = state.navigateBack) {
        if (state.navigateBack == true) {
            navController.popBackStack()
            viewModel.accept(ImageScreenIntent.Navigated)
        }
    }

    ImageScreenContent(state) { event -> viewModel.accept(event) }
}

@Composable
fun ImageScreenContent(
    state: ImageScreenState,
    sendEvent: (ImageScreenIntent) -> Unit
) {
    Box {
        state.image?.bitmap?.asImageBitmap()?.let {
            ZoomableImage(
                modifier = Modifier.fillMaxSize(),
                imageBitmap = it
            )
        }
        Image(
            painter = painterResource(id = R.drawable.white_back_arrow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 60.dp, start = 16.dp)
                .clickable { sendEvent(ImageScreenIntent.NavigateBack) }
        )
        Box(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 6.dp, bottom = 8.dp)) {
            state.image?.let { ImageDateText(text = it.date) }
        }
    }
}