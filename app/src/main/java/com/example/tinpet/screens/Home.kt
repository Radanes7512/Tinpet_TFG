package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 20.dp, 20.dp, 200.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.onPrimary)
            ) {
                Text(text = "Home")
            }
        }
       // Spacer(Modifier.size(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(text = "Home2")
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