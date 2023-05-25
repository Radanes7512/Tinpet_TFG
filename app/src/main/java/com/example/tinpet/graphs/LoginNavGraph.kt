package com.example.tinpet.graphs

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tinpet.AppScreens
import com.example.tinpet.screens.LoginScreen
import com.example.tinpet.viewModels.LoginViewModel

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
                onRegClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Signup.route)
                },
                viewModel = LoginViewModel(LocalContext.current)
            )
        }
    }
}
sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
}