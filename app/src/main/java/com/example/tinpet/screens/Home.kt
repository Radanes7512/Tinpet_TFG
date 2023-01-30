package com.example.tinpet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
        ){
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = null
            )

        }
    }
}

@Composable
@Preview
fun HomeScreenPreviewLT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Composable
@Preview
fun HomeScreenPreviewDT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = true) {
        HomeScreen()
    }
}