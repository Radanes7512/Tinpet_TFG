package com.example.tinpet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.screens.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination =  BottomBarScreen.Home.route
    ){
        composable(route=BottomBarScreen.Places.route){
            PlacesScreen()
        }
        composable(route=BottomBarScreen.Connect.route){
            ConnectScreen()
        }
        composable(route=BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route=BottomBarScreen.Chat.route){
            ChatScreen()
        }
        composable(route=BottomBarScreen.Profile.route){
            ProfileScreen()
        }


    }
}