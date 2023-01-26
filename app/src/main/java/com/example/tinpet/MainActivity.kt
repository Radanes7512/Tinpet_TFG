package com.example.tinpet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.screens.LoginScreen
import com.example.tinpet.ui.theme.TinPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinPetTheme {
                val navController = rememberNavController()
                MainScreen(navController)
                //LoginScreen(navController)


            }
        }
    }
}

