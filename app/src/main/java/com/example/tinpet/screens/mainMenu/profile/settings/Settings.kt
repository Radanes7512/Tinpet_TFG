package com.example.tinpet.screens.mainMenu.profile.settings

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onNotifyClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val logoutConfirm = remember { mutableStateOf(false)}

    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ajustes",
                        fontFamily = abrilFatface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    )
    //endregion
    // region CUERPO
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
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
                            text = "Notificaciones",
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
            /*title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                    //horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp),
                        painter = if (isSystemInDarkTheme()) {
                            painterResource(R.drawable.icon_pawprint)
                        } else {
                            painterResource(R.drawable.icon_pawprint)
                        },
                        contentDescription = null
                    )
                }
            },*/
            text = {
                Text(
                    text = "¿Desea cerrar sesión?",
                    fontSize = 20.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
            },
            shape = RoundedCornerShape(size = 30.dp),
            backgroundColor = MaterialTheme.colors.background,
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
                        onClick = {  onCloseClick() }
                    ) {
                        Text(
                            text = "Cerrar sesión",
                            fontFamily = abrilFatface,
                            color = Color.White
                        )
                    }

                }
            }
        )
    }
}
