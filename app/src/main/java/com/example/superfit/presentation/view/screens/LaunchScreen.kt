package com.example.superfit.presentation.view.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.superfit.presentation.viewmodel.LaunchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LaunchScreen(navController: NavController, viewModel: LaunchViewModel = viewModel()) {

    Text(text = "Launch")
}