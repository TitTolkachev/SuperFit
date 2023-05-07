package com.example.superfit.presentation.view.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.view.shared.AuthBrandText

@Composable
fun SignInScreen(navController: NavController) {

    SignInScreenContent()
}

@Composable
fun SignInScreenContent() {

    Image(
        painter = painterResource(id = R.drawable.auth_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    AuthBrandText(padding = PaddingValues(38.dp, 68.dp))
}