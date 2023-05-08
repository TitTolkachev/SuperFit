package com.example.superfit.presentation.navigation.graph

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.superfit.presentation.navigation.AUTH_GRAPH_ROUTE
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.view.screens.signin.SignInScreen
import com.example.superfit.presentation.view.screens.signup.SignUpScreen

@OptIn(ExperimentalMaterial3Api::class)
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
            Log.e("Navigated", "Navigated!!!")
            SignInScreen(navController)
        }
        composable(
            route = Screen.SignUp.route
        ){
            SignUpScreen(navController)
        }
    }
}