package com.example.superfit.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.superfit.presentation.navigation.AUTH_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.SignInScreen
import com.example.superfit.presentation.view.screens.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Launch.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.SignIn.route,
        ){
            SignInScreen(navController)
        }
        composable(
            route = Screen.SignUp.route
        ){
            SignUpScreen(navController)
        }
    }
}