package com.example.superfit.presentation.view.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    MainScreenContent()
}

@Composable
fun MainScreenContent() {

    LazyColumn(
        modifier = Modifier
            .border(
                width = 0.dp,
                color = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .background(Color.White),
    ) {
        item {
            Poster()
        }
    }
}

@Composable
fun Poster() {

    Box(contentAlignment = Alignment.Center) {

        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(id = R.drawable.main_screen_poster),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                        .height(24.dp)
                )
            }
        }

        Text(
            text = LocalContext.current.getString(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}