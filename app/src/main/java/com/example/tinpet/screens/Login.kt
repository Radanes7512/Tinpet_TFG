package com.example.tinpet.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.*
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {

            // LOGO SUPERIOR
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Logo TinPet
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                            //.background(color = MaterialTheme.colors.primaryVariant),
                        contentAlignment = Alignment.Center
                        //horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Image(
                            modifier = Modifier
                                .size(64.dp)
                                .padding(8.dp),
                            painter =painterResource(R.drawable.icon_pawprint),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(R.string.app_name),
                            fontSize = 32.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
            // INICIO DE SESIÓN
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text(
                        modifier = Modifier.padding(5.dp),
                        textAlign = TextAlign.Center,
                        text = (stringResource(R.string.login_ES)),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            // USER NAME
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Nombre del usuario
                    var name by remember {mutableStateOf("")}
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it},
                        label = { Text("Usuario") },
                        placeholder = { Text("Número de teléfono") },
                        leadingIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.PhoneAndroid,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
                // USER PASSWORD
                var password by remember {mutableStateOf("")}
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Nombre del usuario
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        placeholder = { Text("Introducir contraseña") },
                        leadingIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.Password,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
            item {
            // BOTON ACCEDER
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            navController.navigate(route = AppScreens.Home.route)
                        }
                    ) {
                        Text(
                            text = "Acceder",
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreviewLT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = false) {
        LoginScreen(navController)
    }
}

@Composable
@Preview
fun LoginScreenPreviewDT() {
    val navController = rememberNavController()
    TinPetTheme(darkTheme = true) {
        LoginScreen(navController)
    }
}