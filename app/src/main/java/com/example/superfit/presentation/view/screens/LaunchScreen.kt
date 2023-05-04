package com.example.superfit.presentation.view.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.viewmodel.LaunchViewModel

@Composable
fun LaunchScreen(navController: NavController, viewModel: LaunchViewModel = hiltViewModel()) {

    Text(text = "Launch")
}