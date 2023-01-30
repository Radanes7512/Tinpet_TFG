package com.example.tinpet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.screens.*

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination =  AppScreens.Home.route
    ){
        composable(route=AppScreens.Places.route){
            PlacesScreen()
        }
        composable(route=AppScreens.Connect.route){
            ConnectScreen()
        }
        composable(route=AppScreens.Home.route){
            HomeScreen(navController)
        }
        composable(route=AppScreens.Chat.route){
            ChatScreen()
        }
        composable(route=AppScreens.Profile.route){
            ProfileScreen(navController)
        }
        composable(route= AppScreens.Settings.route){
            SettingsScreen(navController)
        }
        composable(route = AppScreens.Login.route){
            LoginScreen(navController)
        }
    }
}




