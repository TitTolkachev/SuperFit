package com.example.superfit.presentation.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.superfit.presentation.navigation.ROOT_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.auth.launch.LaunchScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Launch.route,
        route = ROOT_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Launch.route
        ) {
            LaunchScreen(navController = navController)
        }
        authNavGraph(navController = navController)
        mainNavGraph(navController = navController)
    }
}