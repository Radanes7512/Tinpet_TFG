package com.example.tinpet.screens.mainMenu.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.graphs.NavGraph
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController(),
    onSetClick: () -> Unit,
    onPetClick: () -> Unit,
    onFrdClick: () -> Unit,
    onRqtClick: () -> Unit
) {
    NavGraph(navController = navController)
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Nombre del usuario
                    Text(
                        modifier = Modifier.padding(5.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.user_name).uppercase(),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                    // Botón de Ajustes
                    Button(
                        onClick = { onSetClick() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = null
                        )
                    }
                }
            }

            item {
                // MIS MASCOTAS
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { onPetClick() }
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Mis mascotas",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier
                                //.align(Alignment(0,0,))
                                //.fillMaxWidth()
                                .size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_pawprint),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
                // PETICIONES DE AMISTAD
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { onRqtClick() }
                            //.border(1.dp, MaterialTheme.colors.onBackground)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Peticiones de amistad",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier.size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_checkfriends),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
                // AMISTADES
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { onFrdClick() }
                            //.border(1.dp, MaterialTheme.colors.onBackground)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Mis amistades",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier.size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_friends),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreviewLT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = false) {
        ProfileScreen(navController, onSetClick = {}, onFrdClick = {}, onPetClick = {}, onRqtClick = {})
    }
}

@Composable
@Preview
fun ProfileScreenPreviewDT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = true) {
        ProfileScreen(navController, onSetClick = {}, onFrdClick = {}, onPetClick = {}, onRqtClick = {})
    }
}