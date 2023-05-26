package com.example.tinpet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route: String,
    //val title: String,
    val icon: ImageVector
) {
    object Splash : AppScreens(
        route = "splash", icon = Icons.Filled.Splitscreen
    )

    object Index : AppScreens(
        route = "index", icon = Icons.Filled.Icecream
    )

    object Signup : AppScreens(
        route = "signup", icon = Icons.Filled.AppRegistration
    )

    object Verify : AppScreens(
        route = "verify", icon = Icons.Filled.Verified
    )
    // MENÚ INFERIOR

    // PLACES
    object Places : AppScreens(
        route = "places",
        //title = "Places",
        icon = Icons.Filled.Forest
    )

    // HOME
    object Home : AppScreens(
        route = "home",
        //title = "Home",
        icon = Icons.Filled.Pets
    )

    // CHAT
    object Chat : AppScreens(
        route = "chat",
        //title = "Chat",
        icon = Icons.Filled.ChatBubble
    )

    object ChatUsers : AppScreens(
        route = "chatusers",
        //title = "Chat",
        icon = Icons.Filled.ChatBubble
    )

    // PERFIL
    object Profile : AppScreens(
        route = "profile",
        //title = "Profile",
        icon = Icons.Filled.Person
    )
    // DENTRO DEL PERFIL

    // PERFIL MASCOTA
    object PetProfile : AppScreens(
        route = "petprofile", icon = Icons.Filled.Pets
    )

    // AJUSTES
    object Settings : AppScreens(
        route = "settings", icon = Icons.Filled.Menu
    )

    // PETICIONES
    object Requests : AppScreens(
        route = "requests", icon = Icons.Filled.ChildFriendly
    )
    // DENTRO DE AJUSTES

    // SOBRE NOSOTROS
    object AboutUs : AppScreens(
        route = "aboutus", icon = Icons.Filled.QuestionAnswer
    )


}
