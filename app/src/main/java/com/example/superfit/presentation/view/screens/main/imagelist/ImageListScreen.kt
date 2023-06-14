package com.example.superfit.presentation.view.screens.main.imagelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily

@Composable
fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = state.navigateBack) {
        if (state.navigateBack == true) {
            navController.popBackStack()
            viewModel.accept(ImageListScreenIntent.Navigated)
        }
    }

    ImageListScreenContent(state) { event: ImageListScreenIntent -> viewModel.accept(event) }
}

@Composable
fun ImageListScreenContent(
    state: ImageListScreenState,
    sendEvent: (ImageListScreenIntent) -> Unit
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.white_back_arrow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 60.dp, start = 16.dp)
                .clickable { sendEvent(ImageListScreenIntent.NavigateBack) }
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 96.dp, start = 12.dp, end = 12.dp),
            columns = GridCells.Fixed(3)
        ) {
            state.galleryBlocks.forEach {
                item(span = { GridItemSpan(this.maxLineSpan) }) {
                    Box(modifier = Modifier.padding(start = 4.dp)) {
                        Text(
                            text = it.title,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            color = Color.White,
                            fontFamily = montserratFamily,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                }
                items(it.images.size) { imageIndex ->
                    Image(
                        bitmap = it.images[imageIndex].bitmap.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                            .clickable {
                                sendEvent(
                                    ImageListScreenIntent.ShowImage(it.images[imageIndex])
                                )
                            }
                    )
                }
            }
        }
    }
}