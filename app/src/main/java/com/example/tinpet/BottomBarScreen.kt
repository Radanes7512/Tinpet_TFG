package com.example.tinpet

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Email
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

sealed class BottomBarScreen(
    val route: String,
    //val title: String,
    val icon: ImageVector
){
    object Places : BottomBarScreen(
        route = "places",
        //title = "Places",
        icon = Icons.Filled.Forest
        //R.drawable.icon_places_white
    )
    object Connect : BottomBarScreen(
        route = "connect",
        //title = "Connect",
        icon = Icons.Filled.Search
    )
    object Home : BottomBarScreen(
        route = "home",
        //title = "Home",
        icon = Icons.Filled.Pets
    )
    object Chat : BottomBarScreen(
        route = "chat",
        //title = "Chat",
        icon = Icons.Filled.ChatBubble
    )
    object Profile : BottomBarScreen(
        route = "profile",
        //title = "Profile",
        icon = Icons.Filled.Person

    )


}
