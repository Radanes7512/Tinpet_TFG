package com.example.tinpet.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.tinpet.MainScreen


@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            MainScreen()
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