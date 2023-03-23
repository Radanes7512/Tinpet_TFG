package com.example.tinpet.screens.mainMenu.profile.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit
) {
    var enablePushNotifications by remember { mutableStateOf(true) }
    var enableNotificationsSound by remember { mutableStateOf(true) }

    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notificaciones",
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
                .padding(16.dp)
                .fillMaxSize()
        ) {
            //region Interruptor para activar/desactivar notificaciones push
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(1.dp, MaterialTheme.colors.secondary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .border(1.dp,Color.Red),
                        text = "Notificaciones push",
                        fontSize = 20.sp,
                    )

                    Switch(
                        checked = enablePushNotifications,
                        onCheckedChange = { enablePushNotifications = it },
                        modifier = Modifier
                            .border(1.dp,Color.Magenta)
                    )
                }

                // Más opciones de notificaciones aquí...
            }
            //endregion
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(1.dp, MaterialTheme.colors.secondary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .border(1.dp,Color.Red),
                        text = "Sonido de las notificaciones",
                        fontSize = 20.sp,
                    )

                    Switch(
                        checked = enableNotificationsSound,
                        onCheckedChange = { enableNotificationsSound = it },
                        modifier = Modifier
                            .border(1.dp,Color.Magenta)
                    )
                }
            }
        }
    }
    //endregion

}



