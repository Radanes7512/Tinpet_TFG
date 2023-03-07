package com.example.tinpet.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.tinpet.AppScreens
import com.example.tinpet.MainScreen
import com.example.tinpet.screens.*
import com.example.tinpet.screens.AddPetScreen


@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = AppScreens.Splash.route
    ) {
        authNavGraph(navController)
        composable(Graph.MAIN) {
            MainScreen()
        }
        composable(AppScreens.Splash.route){
            SplashScreen(navController)
        }
        composable(AppScreens.Signup.route){
            SignupScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.SmsInput.route)
                },
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                viewModel = LoginViewModel(LocalContext.current)
            )
        }

        composable(AppScreens.SmsInput.route){
            InputSmsNumScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Addpet.route)
                },
                viewModel = LoginViewModel(LocalContext.current)
            )
        }

        composable(AppScreens.Addpet.route){
            AddPetScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                },
                viewModel = LoginViewModel(LocalContext.current)
            )
        }
        composable(AppScreens.Index.route){
            IndexScreen(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                onSignupClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Signup.route)
                }
            )
        }
        /*composable(
            route = Graph.AUTHENTICATION,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "android-app://androidx.navigation/auth_graph"
                })
        ) {
        }*/
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "home_graph"
}