package com.example.tinpet.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tinpet.AppScreens
import com.example.tinpet.MainScreen
import com.example.tinpet.screens.*
import com.example.tinpet.screens.mainMenu.*
import com.example.tinpet.screens.mainMenu.profile.*
import com.example.tinpet.screens.mainMenu.profile.settings.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = AppScreens.Home.route
    ){
        composable(route=AppScreens.Places.route){
            PlacesScreen()
        }
        composable(route=AppScreens.Connect.route){
            ConnectScreen()
        }
        composable(route=AppScreens.Home.route){
            HomeScreen(
                viewModel= HomeViewModel()
            )
        }
        composable(route=AppScreens.ChatUsers.route){
            ChatUsersScreen(
                navController = navController,
                viewModel= ChatViewModel()
            )
        }
        composable(route=AppScreens.Chat.route + "/{chatUserId}"){ backStackEntry ->
            val chatUserId = backStackEntry.arguments?.getString("chatUserId") ?: ""
            ChatScreen(
                chatUserId = chatUserId,
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.ChatUsers.route)
                },
                onPetClick = {
                    navController.navigate(AppScreens.PetProfile.route)
                },
                viewModel= ChatViewModel()
            )
        }
        composable(route = AppScreens.Profile.route){
            ProfileScreen(
                onSetClick = {
                    navController.navigate(AppScreens.Settings.route)
                },
                onPetClick = {
                    navController.navigate(AppScreens.Pets.route)
                },
                onFrdClick = {
                    navController.navigate(AppScreens.Friends.route)
                },
                onRqtClick ={
                    navController.navigate(AppScreens.Requests.route)
                },
                viewModel = LoginViewModel(LocalContext.current, navController)
            )
        }
        composable(route = AppScreens.Pets.route){
            PetsScreen(
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Profile.route)
                },
                onAddClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Addpet.route)
                },
                onPetClick = {
                    navController.navigate(AppScreens.PetProfile.route)
                },
                homeViewModel= HomeViewModel()
            )
        }
        composable(AppScreens.PetProfile.route){
            PetProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onChatClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Chat.route)
                }
            )
        }
        composable(route = AppScreens.Requests.route){
            RequestScreen(
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Profile.route)
                },
                onPetClick = {
                    navController.navigate(AppScreens.PetProfile.route)
                },
                onChatClick = {
                    navController.navigate(AppScreens.Chat.route)
                },
                        viewModel = RequestsViewModel()
            )
        }
        composable(route = AppScreens.Friends.route){
            FriendsScreen(
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Profile.route)
                },
                onPetClick = {
                    navController.navigate(AppScreens.PetProfile.route)
                },
                onChatClick = {
                    navController.navigate(AppScreens.Chat.route)
                }
            )
        }
        composable(route = AppScreens.Notifications.route){
            NotificationsScreen(
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Settings.route)
                }
            )
        }
        composable(route = AppScreens.AboutUs.route){
            AboutUsScreen(
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Settings.route)
                }
            )
        }
        composable(route = AuthScreen.Login.route,deepLinks = listOf(navDeepLink {uriPattern = "android-app://androidx.navigation/auth_graph"})        ){
            LoginScreen (
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                },
                onRegClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Signup.route)
                },
                viewModel = LoginViewModel(LocalContext.current, navController)
            )
        }
        composable(AppScreens.Signup.route){
            SignupScreen (
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Verify.route)
                },
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },

                viewModel = LoginViewModel(LocalContext.current, navController)

            )

        }
        composable(AppScreens.Verify.route){
            VerifyEmailScreen(
                viewModel = LoginViewModel(LocalContext.current, navController),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                }
            )
        }
        composable(AppScreens.Addpet.route){
            AddPetScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                viewModel = LoginViewModel(LocalContext.current, navController)
            )
        }
        composable(route = AppScreens.Settings.route){
            SettingsScreen(
                onBackClick = {
                    navController.navigate(AppScreens.Profile.route)
                },
                onCloseClick = {
                    /*navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)*/
                    navController.navigate(
                        route = "login",
                        builder = {
                            popUpTo(route = "home"){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    )
                    //navOptions { popUpTo(Graph.AUTHENTICATION) }
                    //navController.navigate(URLEncoder.encode(Graph.AUTHENTICATION, StandardCharsets.UTF_8.toString()))
                    //navController.navigate(NavDeepLinkRequest(Uri.parse("android-app://androidx.navigation/auth_graph")))
                    //navController.navigate(NavDeepLinkRequest.Builder.fromUri("android-app://androidx.navigation/auth_graph".toUri()).build())
                },
                onNotifyClick = {
                    navController.navigate(AppScreens.Notifications.route)
                },
                onAboutClick = {
                    navController.navigate(AppScreens.AboutUs.route)
                }
            )
        }
    }
}






