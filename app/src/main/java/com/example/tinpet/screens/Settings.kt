package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.AppScreens
import com.example.tinpet.R
import com.example.tinpet.graphs.Graph
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
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
                    // Ajustes
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        text = (stringResource(R.string.settings_ES)),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )


                }
            }

            item{
                // NOTIFICACIONES
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
                            //.border(1.dp, MaterialTheme.colors.onBackground)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically),
                            text = "Notifiaciones",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier
                                .size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_notifications),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
                // SOBRE NOSOTROS
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
                                //.border(1.dp, MaterialTheme.colors.onBackground)
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.primary)
                                .padding(10.dp)

                        ) {
                            Text(
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically),
                                text = "Sobre nosotros",
                                fontSize = 15.sp,
                                fontFamily = abrilFatface,
                                color = MaterialTheme.colors.onPrimary
                            )
                            Image(
                                modifier = Modifier
                                    .size(25.dp, 25.dp),
                                painter = painterResource(R.drawable.icon_aboutus),
                                contentDescription = null,
                                alignment = Alignment.Center
                            )
                        }
                    }
                // CERRAR SESIÓN
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { onCloseClick() }
                            //.border(1.dp, MaterialTheme.colors.onBackground)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically),
                            text = "Cerrar sesión",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier
                                .size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_logout),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
            }

        }


    }

}
/*
@Composable
@Preview
fun SettingScreenPreviewLT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = false) {
        SettingsScreen(navController)
    }
}

@Composable
@Preview
fun SettingScreenPreviewDT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = true) {
        SettingsScreen(navController)
    }
}*/