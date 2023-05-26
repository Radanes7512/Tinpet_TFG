package com.example.tinpet.screens.mainMenu.profile.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.Montserrat
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutUsScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sobre nosotros",
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
        },
        bottomBar = {
            BottomBar()
        }
    )
    //endregion
    // region CUERPO
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(16.dp))
                //region REGION SOBRE NOSOTROS
                Text(
                    textAlign = TextAlign.Justify,
                    text = "Queremos presentarte nuestra aplicación móvil, diseñada para los apasionados por el mundo de las mascotas. Tiene como objetivo ayudarlas a encontrar amigos y reducir su estrés durante los paseos diarios.\n" +
                            "\n" +
                            "TinPet fue desarrollada tras darnos cuenta de que muchas veces las mascotas, al igual que las personas, necesitan socializar y hacer amigos. También pueden sufrir estrés y aburrimiento al no tener con quién interactuar en sus paseos diarios, lo que puede llevar a comportamientos no deseados en el hogar. Con nuestra aplicación, queremos cambiar eso y ayudar a nuestras mascotas a conectarse entre sí.\n" +
                            "\n" +
                            "El funcionamiento de TinPet es muy sencillo. Los dueños pueden crear perfiles de sus mascotas y agregar información como su nombre, edad y categorías. También pueden agregar una foto para mostrar su personalidad. Luego, pueden encontrar en la aplicación otros perfiles que estén cerca de ellos y que compartan intereses similares.\n" +
                            "\n" +
                            "Además, nuestra aplicación también ofrece una función de \"chat\" para que una vez que encuentren una mascota compatible, los dueños se puedan poner en contacto y organizar futuras citas de juego.\n" +
                            "\n" +
                            "Los creadores de TinPet somos dos estudiantes de informática, Andrés y Rodrigo. Nos conocimos en una formación y nos dimos cuenta de que compartimos una visión común: queríamos crear algo que ayudara a las mascotas a tener una vida más feliz y saludable. Después de muchos meses de trabajo duro, lanzamos TinPet, nuestra aplicación móvil que conecta a mascotas de todo el mundo y da visibilidad a parques y áreas caninas.\n" +
                            "\n" +
                            "Nos enorgullece decir que nuestra aplicación ha sido bien recibida por los que ya han podido probarla. Nos encanta ver cómo las mascotas hacen amistades gracias a nuestra aplicación. También nos encanta escuchar las historias de éxito de nuestros usuarios, como mascotas que se han conocido a través de TinPet y ahora son inseparables.\n" +
                            "\n" +
                            "Esperamos que disfrutes usando TinPet tanto como nosotros hemos disfrutamos creándola. ¡Únete ya a la comunidad de TinPet!\n" +
                            "\n\n"
                )
                //endregion
                //region REGION EQUIPO
                //TITULO
                Text(
                    text = "El equipo detrás de " + stringResource(R.string.app_name),
                    fontFamily = abrilFatface,
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.onBackground
                )
                // DESARROLLADORES
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // DESARROLLADOR 1
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // FOTO PERFIL
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp),
                            painter = painterResource(R.drawable.andres_profile),
                            contentDescription = null
                        )
                        // NOMBRE
                        Text(text = "Andrés González", fontSize = 20.sp)
                        // APODO
                        Text(text = "Radamanes", fontSize = 10.sp)
                        Spacer(modifier = Modifier.padding(5.dp))
                        // PUESTO
                        Text(text = "BACKEND DEVELOPER", fontSize = 10.sp, fontFamily = Montserrat)
                        Spacer(modifier = Modifier.padding(5.dp))
                        // LINKS
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Whatsapp,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Share,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.RssFeed,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    //DESARROLLADOR 2
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // FOTO PERFIL
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp),
                            painter = painterResource(R.drawable.roy_profile),
                            contentDescription = null
                        )
                        // NOMBRE
                        Text(text = "Rodrigo Domínguez", fontSize = 20.sp)
                        // APODO
                        Text(text = "LambdaR", fontSize = 10.sp)
                        Spacer(modifier = Modifier.padding(5.dp))
                        // PUESTO
                        Text(text = "FRONTEND DEVELOPER", fontSize = 10.sp, fontFamily = Montserrat)
                        Spacer(modifier = Modifier.padding(5.dp))
                        // LINKS
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Chat,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Share,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.RssFeed,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                        }
                    }
                }
                //endregion
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
    //endregion
}

@Composable
fun BottomBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            "2023 " +
                    stringResource(
                        R.string.app_name
                    ),
            fontFamily = abrilFatface
        )
    }
}
