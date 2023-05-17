package com.example.superfit.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.superfit.presentation.navigation.MAIN_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.exercises.ExercisesScreen
import com.example.superfit.presentation.view.screens.main.MainScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Launch.route,
        route = MAIN_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Main.route,
        ) {
            MainScreen(navController)
        }
        composable(
            route = Screen.Exercises.route,
        ) {
            ExercisesScreen(navController)
        }
    }
}