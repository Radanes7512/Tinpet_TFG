package com.example.tinpet.screens.mainMenu.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun FriendsScreen(
    onBackClick: () -> Unit
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
                        text = "Amistades",
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                }

            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { }
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Scooby".uppercase(),
                            fontSize = 32.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier
                                //.align(Alignment(0,0,))
                                //.fillMaxWidth()
                                .clip(CircleShape)
                                .size(50.dp)
                                .align(alignment = Alignment.CenterVertically),
                            painter = painterResource(R.drawable.default_pet_2),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun FriendsScreenPreviewDT() {
    TinPetTheme(darkTheme = false) {
        FriendsScreen(onBackClick = {})
    }
}
@Composable
@Preview
fun FriendsScreenPreviewLT() {
    TinPetTheme(darkTheme = true) {
        FriendsScreen(onBackClick = {})
    }
}

