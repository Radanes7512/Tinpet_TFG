package com.example.tinpet.screens.mainMenu.profile.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.tinpet.R
import com.example.tinpet.viewModels.LoginViewModel
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    viewModel: LoginViewModel,
    onAboutClick: () -> Unit,
) {
    val logoutConfirm = remember { mutableStateOf(false) }
    val context= LocalContext.current
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
        }/*,
        bottomBar = {
            BottomSBar()
        }*/
    )
    //endregion
    //region CUERPO
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                //region NOTIFICACIONES
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable {
                                val intent = Intent()
                                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                                intent.putExtra("android.provider.extra.APP_PACKAGE", "com.example.tinpet")
                                if (intent.resolveActivity(context.packageManager) != null) {
                                    ContextCompat.startActivity(context, intent, Bundle())
                                }
                            }
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
                //endregion
                //region SOBRE NOSOTROS
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
                //endregion
                //region CERRAR SESIÓN
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
                //endregion
            }
        }
    }
    if (logoutConfirm.value) {
        AlertDialog(
            onDismissRequest = {
                logoutConfirm.value = false
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
                        onClick = {
                            onCloseClick()
                            viewModel.clearUserInfo(context)
                        }
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

@Composable
fun BottomSBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
        ){
            Text(text="Borrar cuenta")
        }
    }
}