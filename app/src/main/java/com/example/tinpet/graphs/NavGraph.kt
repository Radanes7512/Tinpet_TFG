package com.example.tinpet.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.AppScreens
import com.example.tinpet.screens.*
import com.example.tinpet.screens.mainMenu.*
import com.example.tinpet.screens.mainMenu.profile.*
import com.example.tinpet.screens.mainMenu.profile.settings.*
import com.example.tinpet.viewModels.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController, route = Graph.MAIN, startDestination = AppScreens.Home.route
    ) {
        composable(route = AppScreens.Places.route) {
            PlacesScreen(
                viewModel = PlacesViewModel()
            )
        }
        composable(route = AppScreens.Home.route) {
            HomeScreen(
                viewModel = HomeViewModel()
            )
        }
        composable(route = AppScreens.ChatUsers.route) {
            ChatUsersScreen(
                navController = navController, viewModel = ChatViewModel()
            )
        }
        composable(route = AppScreens.Chat.route + "/{chatUserId}") { backStackEntry ->
            val chatUserId = backStackEntry.arguments?.getString("chatUserId") ?: ""
            ChatScreen(chatUserId = chatUserId, onBackClick = {
                navController.popBackStack()
                navController.navigate(AppScreens.ChatUsers.route)
            },
                viewModel = ChatViewModel()
            )
        }
        composable(route = AppScreens.Profile.route) {
            ProfileScreen(
                onPetClick = {
                navController.navigate(AppScreens.PetProfile.route)
            }, onRqtClick = {
                navController.navigate(AppScreens.Requests.route)
            }, viewModel = LoginViewModel(LocalContext.current)
            )
        }
        composable(AppScreens.PetProfile.route) {
            PetProfileScreen(onBackClick = {
                navController.popBackStack()
            },
                viewModel = ProfileViewModel()
            )
        }
        composable(route = AppScreens.Requests.route) {
            RequestScreen(
                onBackClick = {
                navController.popBackStack()
                navController.navigate(AppScreens.Profile.route)
            }, onPetClick = {
                navController.navigate(AppScreens.PetProfile.route)
            },
                viewModel = RequestsViewModel(LocalContext.current)
            )
        }
        composable(route = AppScreens.AboutUs.route) {
            AboutUsScreen(onBackClick = {
                navController.popBackStack()
                navController.navigate(AppScreens.Settings.route)
            })
        }
        composable(route = AuthScreen.Login.route, deepLinks = listOf(navDeepLink {
            uriPattern = "android-app://androidx.navigation/auth_graph"
        })) {
            LoginScreen(onClick = {
                navController.popBackStack()
                navController.navigate(Graph.MAIN)
            }, onRegClick = {
                navController.popBackStack()
                navController.navigate(AppScreens.Signup.route)
            }, viewModel = LoginViewModel(LocalContext.current)
            )
        }
        composable(AppScreens.Signup.route) {
            SignupScreen(onClick = {
                navController.popBackStack()
                navController.navigate(AppScreens.Verify.route)
            }, onBackClick = {
                navController.popBackStack()
                navController.navigate(Graph.AUTHENTICATION)
            },

                viewModel = LoginViewModel(LocalContext.current)

            )

        }
        composable(AppScreens.Verify.route) {
            VerifyEmailScreen(viewModel = LoginViewModel(LocalContext.current), onClick = {
                navController.popBackStack()
                navController.navigate(Graph.AUTHENTICATION)
            })
        }
        composable(route = AppScreens.Settings.route) {
            SettingsScreen(onBackClick = {
                navController.navigate(AppScreens.Profile.route)
            }, onCloseClick = {
                navController.navigate(route = "login", builder = {
                    popUpTo(route = "home") {
                        inclusive = true
                    }
                    launchSingleTop = true
                })
            }, viewModel = LoginViewModel(LocalContext.current), onAboutClick = {
                navController.navigate(AppScreens.AboutUs.route)
            }

            )
        }
    }
}






