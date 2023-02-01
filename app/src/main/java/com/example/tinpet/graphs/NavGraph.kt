package com.example.tinpet

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.*
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.graphs.AuthScreen
import com.example.tinpet.graphs.Graph
import com.example.tinpet.screens.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN ,
        startDestination =  AppScreens.Home.route
    ){
        composable(route=AppScreens.Places.route){
            PlacesScreen()
        }
        composable(route=AppScreens.Connect.route){
            ConnectScreen()
        }
        composable(
            route=AppScreens.Home.route){
            HomeScreen()
        }
        composable(route=AppScreens.Chat.route){
            ChatScreen()
        }
        composable(route = AppScreens.Profile.route){
            ProfileScreen(
                onSetClick = {
                    navController.navigate(AppScreens.Settings.route)
                }
            )
        }
        composable(route = AuthScreen.Login.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "android-app://androidx.navigation/auth_graph"
                })){
            LoginScreen (
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                }
                    )
        }
        composable(
            route = AppScreens.Settings.route
        ){
            SettingsScreen(

                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Profile.route)
                },
                onCloseClick = {
                    //navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                    //navController.navigate(URLEncoder.encode(Graph.AUTHENTICATION, StandardCharsets.UTF_8.toString()))
                    //navController.navigate(NavDeepLinkRequest(Uri.parse("android-app://androidx.navigation/auth_graph")))
                    //navController.navigate(NavDeepLinkRequest.Builder.fromUri("android-app://androidx.navigation/auth_graph".toUri()).build())
                }
            )
        }
    }
}






