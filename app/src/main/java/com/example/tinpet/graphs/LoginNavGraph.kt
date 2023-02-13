package com.example.tinpet.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tinpet.AppScreens
import com.example.tinpet.screens.LoginScreen
import com.example.tinpet.screens.LoginViewModel
import com.example.tinpet.screens.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(
            route = AuthScreen.Login.route
        ) {
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                },
                viewModel = LoginViewModel()
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
}