package com.example.tinpet.screens.mainMenu.profile.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onNotifyClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val logoutConfirm = remember { mutableStateOf(false)}
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
                            .clickable { onNotifyClick() }
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
                                .clickable { onAboutClick() }
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
                            .clickable { logoutConfirm.value = true }
                            //.clickable { onCloseClick() }
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
    if (logoutConfirm.value) {
        AlertDialog(
            onDismissRequest = {
                logoutConfirm.value = false
            },
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primaryVariant),
                    contentAlignment = Alignment.Center
                    //horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        painter = if (isSystemInDarkTheme()) {
                            painterResource(R.drawable.icon_pawprint_black)
                        } else {
                            painterResource(R.drawable.icon_pawprint_white)
                        },
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            },
            text = {
                Text(
                    text = "¿Desea cerrar sesión?",
                    fontSize = 20.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
            },
            shape = RoundedCornerShape(size = 30.dp),
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        modifier = Modifier
                            .padding(10.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        shape = RoundedCornerShape(size = 30.dp),
                        onClick = { logoutConfirm.value = false }
                    ) {
                        Text(
                            text = "Atrás",
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(10.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.error),
                        shape = RoundedCornerShape(size = 30.dp),
                        //elevation = 10,
                        onClick = {  onCloseClick()}
                    ) {
                        Text(
                            text = "Cerrar sesión",
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }

                }
            }
        )
    }
}

@Composable
@Preview
fun SettingScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        SettingsScreen(onCloseClick = {}, onBackClick = {}, onAboutClick = {}, onNotifyClick = {})
    }
}

@Composable
@Preview
fun SettingScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        SettingsScreen(onCloseClick = {}, onBackClick = {}, onAboutClick = {}, onNotifyClick = {})
    }
}