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
}