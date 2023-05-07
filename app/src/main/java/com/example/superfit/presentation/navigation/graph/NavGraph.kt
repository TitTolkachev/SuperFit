package com.example.superfit.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.superfit.presentation.navigation.ROOT_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.launch.LaunchScreen

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
        // TODO(Добавить остальные нав графы)
        //homeNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}