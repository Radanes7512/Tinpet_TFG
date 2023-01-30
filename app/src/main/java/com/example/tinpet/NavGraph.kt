package com.example.tinpet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.graphs.Graph
import com.example.tinpet.screens.*

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME ,
        startDestination =  AppScreens.Home.route
    ){
        composable(route=AppScreens.Places.route){
            PlacesScreen()
        }
        composable(route=AppScreens.Connect.route){
            ConnectScreen()
        }
        composable(route=AppScreens.Home.route){
            HomeScreen()
        }
        composable(route=AppScreens.Chat.route){
            ChatScreen()
        }
        composable(route=AppScreens.Profile.route){
            ProfileScreen(
                onClick = {
                navController.navigate(Graph.SETTINGS)
            })
        }
    }
}




