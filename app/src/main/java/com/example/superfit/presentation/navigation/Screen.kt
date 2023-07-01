package com.example.superfit.presentation.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val MAIN_GRAPH_ROUTE = "main"

sealed class Screen (val route: String) {
    object Launch: Screen(route = "launch_screen")
    object SignIn: Screen(route = "sign_up_screen")
    object SignUp: Screen(route = "sign_in_screen")
    object Main: Screen(route = "main_screen")
    object Exercises: Screen(route = "exercises_screen")
    object Body: Screen(route = "body_screen")
    object Image: Screen(route = "image_screen")
    object ImageList: Screen(route = "image_list_screen")
    object Crunch: Screen(route = "crunch_screen")
    object Plank: Screen(route = "plank_screen")
    object PushUps: Screen(route = "push_ups_screen")
    object Running: Screen(route = "running_screen")
    object Squats: Screen(route = "squats_screen")
    object Success: Screen(route = "success_screen")
    object UnSuccess: Screen(route = "un_success_screen")
    object Progress: Screen(route = "progress_screen")
    object Statistics: Screen(route = "statistics_screen")
}