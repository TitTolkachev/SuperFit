package com.example.superfit.presentation.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"

sealed class Screen (val route: String) {
    object Launch: Screen(route = "launch_screen")
    object SignIn: Screen(route = "sign_up_screen")
    object SignUp: Screen(route = "sign_in_screen")
}