package com.example.tinpet.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.MainScreen
import com.example.tinpet.screens.ProfileScreen

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        settingsNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            MainScreen()
        }
        composable(route = Graph.PROFILE) {
            ProfileScreen(
                onClick = {
                navController.navigate(Graph.SETTINGS)
            })
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val SETTINGS = "settings_graph"
    const val PROFILE = "profile_graph"
}