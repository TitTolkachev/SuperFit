package com.example.superfit.presentation.view.screens.auth.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.shared.auth.AuthBrandText

@Composable
fun LaunchScreen(navController: NavController, viewModel: LaunchViewModel = hiltViewModel()) {

    val state = viewModel.state

    LaunchedEffect(key1 = state) {

        if (state.isFirstEnter == true) {
            navController.popBackStack()
            navController.navigate(Screen.SignUp.route)
        }

        if (state.isAuthorized != null) {
            if (state.isAuthorized == true) {

                //TODO()
                navController.popBackStack()
                navController.navigate(Screen.SignUp.route)
            } else {
                navController.popBackStack()
                navController.navigate(Screen.SignUp.route)
            }
        }
    }

    LaunchScreenContent()
}

@Composable
fun LaunchScreenContent() {

    Image(
        painter = painterResource(id = R.drawable.auth_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    AuthBrandText(padding = PaddingValues(top = 139.dp))
}