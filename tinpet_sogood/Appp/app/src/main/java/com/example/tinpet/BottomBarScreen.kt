package com.example.tinpet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.Email
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Places : BottomBarScreen(
        route = "places",
        title = "Places",
        icon = Icons.Default.Place
    )
    object Connect : BottomBarScreen(
        route = "connect",
        title = "Connect",
        icon = Icons.Default.Phone
    )
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Chat : BottomBarScreen(
        route = "chat",
        title = "Chat",
        icon = Icons.Rounded.Email
    )
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )


}
