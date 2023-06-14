package com.example.superfit.presentation.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.superfit.presentation.navigation.MAIN_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.exercises.crunch.CrunchScreen
import com.example.superfit.presentation.view.screens.main.body.BodyScreen
import com.example.superfit.presentation.view.screens.main.exercises.ExercisesScreen
import com.example.superfit.presentation.view.screens.main.image.ImageScreen
import com.example.superfit.presentation.view.screens.main.main.MainScreen

@RequiresApi(Build.VERSION_CODES.P)
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
            route = Screen.Image.route + "/{imageId}/{imageDate}",
            arguments = listOf(navArgument("imageId") {
                type = NavType.StringType
            }, navArgument("imageDate") {
                type = NavType.StringType
            })
        ) {
            val imageId = it.arguments?.getString("imageId") ?: ""
            val imageDate = it.arguments?.getString("imageDate") ?: ""
            ImageScreen(navController, imageId = imageId, imageDate = imageDate)
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