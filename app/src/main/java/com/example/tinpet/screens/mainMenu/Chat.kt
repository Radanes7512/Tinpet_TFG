package com.example.tinpet.screens.mainMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun ChatScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier
            .fillMaxSize()
        ){
            item {

            }
        }
        Text(
            text = "Chat",
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground

        )
    }
}

@Composable
@Preview
fun ChatScreenPreview() {
ChatScreen()
}