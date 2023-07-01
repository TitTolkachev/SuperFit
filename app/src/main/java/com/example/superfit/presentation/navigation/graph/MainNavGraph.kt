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
import com.example.superfit.presentation.view.screens.exercises.plank.PlankScreen
import com.example.superfit.presentation.view.screens.exercises.pushups.PushUpsScreen
import com.example.superfit.presentation.view.screens.exercises.running.RunningScreen
import com.example.superfit.presentation.view.screens.exercises.squats.SquatsScreen
import com.example.superfit.presentation.view.screens.exercises.success.SuccessScreen
import com.example.superfit.presentation.view.screens.exercises.unsuccess.UnSuccessScreen
import com.example.superfit.presentation.view.screens.main.body.BodyScreen
import com.example.superfit.presentation.view.screens.main.exercises.ExercisesScreen
import com.example.superfit.presentation.view.screens.main.image.ImageScreen
import com.example.superfit.presentation.view.screens.main.imagelist.ImageListScreen
import com.example.superfit.presentation.view.screens.main.main.MainScreen
import com.example.superfit.presentation.view.screens.main.progress.ProgressScreen

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
            ImageListScreen(navController)
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
            PlankScreen(navController)
        }
        composable(
            route = Screen.PushUps.route,
        ) {
            PushUpsScreen(navController)
        }
        composable(
            route = Screen.Running.route,
        ) {
            RunningScreen(navController)
        }
        composable(
            route = Screen.Squats.route,
        ) {
            SquatsScreen(navController)
        }

        composable(
            route = Screen.Success.route + "/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            })
        ) {
            val title = it.arguments?.getString("title") ?: ""
            SuccessScreen(navController, title)
        }
        composable(
            route = Screen.UnSuccess.route + "/{title}/{repeats}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            }, navArgument("repeats") {
                type = NavType.IntType
            })
        ) {
            val title = it.arguments?.getString("title") ?: ""
            val repeats = it.arguments?.getInt("repeats") ?: 0
            UnSuccessScreen(navController, title, repeats)
        }

        composable(
            route = Screen.Progress.route,
        ) {
            ProgressScreen(navController)
        }
        composable(
            route = Screen.Statistics.route,
        ) {
            // TODO
//            SquatsScreen(navController)
        }
    }
}