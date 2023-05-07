package com.example.superfit.presentation.view.screens.launch

import androidx.compose.runtime.Immutable

@Immutable
data class LaunchScreenState(
    val isFirstEnter: Boolean?,
    val isAuthorized: Boolean?,
)