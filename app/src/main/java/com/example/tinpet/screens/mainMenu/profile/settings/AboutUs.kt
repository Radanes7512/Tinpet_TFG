package com.example.tinpet.screens.mainMenu.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun AboutUsScreen(onBackClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Botón de atrás
                    Button(
                        onClick = { onBackClick() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "Sobre nosotros",
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                }

            }
        }
    }
}

@Composable
@Preview
fun AboutUsScreenPreviewDT() {
    TinPetTheme(darkTheme = false) {
        AboutUsScreen(onBackClick = {})
    }
}
@Composable
@Preview
fun AboutUsScreenPreviewLT() {
    TinPetTheme(darkTheme = true) {
        AboutUsScreen(onBackClick = {})
    }
}
