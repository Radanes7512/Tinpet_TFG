package com.example.tinpet

sealed class AppScreens(
    val route: String
    ){
    object Settings : AppScreens(
    route = "settings"
    )
}

