package com.example.tinpet.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tinpet.AppScreens
import com.example.tinpet.screens.ProfileScreen
import com.example.tinpet.screens.SettingsScreen

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SETTINGS,
        startDestination = SetScreen.Settings.route
    ){
        composable(route=SetScreen.Settings.route){
            SettingsScreen(
                onCloseClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                onBackClick ={
                    navController.popBackStack()
                    navController.navigate(Graph.PROFILE)
                }

            )
        }
    }
}

sealed class SetScreen(val route: String) {
    object Settings : SetScreen(route = "SETTINGS")
}
