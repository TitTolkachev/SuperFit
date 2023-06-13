package com.example.superfit.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.superfit.presentation.navigation.MAIN_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.exercises.crunch.CrunchScreen
import com.example.superfit.presentation.view.screens.main.body.BodyScreen
import com.example.superfit.presentation.view.screens.main.exercises.ExercisesScreen
import com.example.superfit.presentation.view.screens.main.image.ImageScreen
import com.example.superfit.presentation.view.screens.main.main.MainScreen

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
        composable(
            route = Screen.Body.route,
        ) {
            BodyScreen(navController)
        }
        composable(
            route = Screen.Image.route,
        ) {
            ImageScreen(navController)
        }
        composable(
            route = Screen.ImageList.route,
        ) {

        }

        // EXERCISES
        composable(
            route = Screen.Crunch.route,
        ) {
            CrunchScreen(navController)
        }
        composable(
            route = Screen.Plank.route,
        ) {
            //CrunchScreen(navController)
        }
        composable(
            route = Screen.PushUps.route,
        ) {
            //CrunchScreen(navController)
        }
        composable(
            route = Screen.Running.route,
        ) {
            //CrunchScreen(navController)
        }
        composable(
            route = Screen.Squats.route,
        ) {
            //CrunchScreen(navController)
        }
    }
}