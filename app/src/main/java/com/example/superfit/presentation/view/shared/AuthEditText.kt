package com.example.superfit.presentation.view.shared

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun AuthEditText() {
    TextField(value = "UserName", onValueChange = {})
}