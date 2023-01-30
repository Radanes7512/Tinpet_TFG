package com.example.tinpet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route: String,
    //val title: String,
    val icon: ImageVector
){
    object Places : AppScreens(
        route = "places",
        //title = "Places",
        icon = Icons.Filled.Forest
    )
    object Connect : AppScreens(
        route = "connect",
        //title = "Connect",
        icon = Icons.Filled.Search
    )
    object Home : AppScreens(
        route = "home",
        //title = "Home",
        icon = Icons.Filled.Pets
    )
    object Chat : AppScreens(
        route = "chat",
        //title = "Chat",
        icon = Icons.Filled.ChatBubble
    )
    object Profile : AppScreens(
        route = "profile",
        //title = "Profile",
        icon = Icons.Filled.Person
    )
    object Settings : AppScreens(
        route = "settings",
        icon = Icons.Filled.Menu

    )
    object  Login : AppScreens(
        route = "login",
        icon = Icons.Filled.Login
    )


}
