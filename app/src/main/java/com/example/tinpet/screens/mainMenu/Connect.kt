package com.example.tinpet.screens.mainMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun ConnectScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Connect",
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
        FloatingActionButton(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun ConnectPreviewDT() {
    TinPetTheme(darkTheme = false) {
        ConnectScreen()
    }
}
@Composable
@Preview
fun ConnectPreviewLT() {
    TinPetTheme(darkTheme = true) {
        ConnectScreen()
    }
}