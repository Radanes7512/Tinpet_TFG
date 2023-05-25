package com.example.tinpet.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.AppScreens
import com.example.tinpet.MainScreen
import com.example.tinpet.screens.*
import com.example.tinpet.screens.mainMenu.HomeScreen
import com.example.tinpet.viewModels.HomeViewModel
import com.example.tinpet.viewModels.LoginViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph(navController: NavHostController, isLoggedIn:Boolean) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (isLoggedIn) Graph.MAIN else AppScreens.Splash.route
    ) {
        authNavGraph(navController)
        composable(Graph.MAIN) {
            MainScreen()
        }
        composable(AppScreens.Home.route){
            HomeScreen(
                viewModel= HomeViewModel()
            )
        }
        composable(AppScreens.Splash.route){
            SplashScreen(navController)
        }
        composable(AppScreens.Signup.route){
            SignupScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Verify.route)
                },
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                viewModel = LoginViewModel(LocalContext.current)
            )
        }
        composable(AppScreens.Verify.route){
            VerifyEmailScreen(
                viewModel = LoginViewModel(LocalContext.current),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                }
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
    }
}
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "home_graph"
}