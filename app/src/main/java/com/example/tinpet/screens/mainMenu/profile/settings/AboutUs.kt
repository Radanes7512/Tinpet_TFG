package com.example.tinpet.screens.mainMenu.profile.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
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
                Spacer(modifier=Modifier.padding(16.dp))
                //region REGION SOBRE NOSOTROS
                Text(
                    textAlign = TextAlign.Justify,
                    text = "Bienvenidos a nuestra página de \"Sobre Nosotros\". " +
                            " Somos dos estudiantes de informática apasionados por el mundo de las mascotas" +
                            " y queremos presentarte nuestra aplicación móvil que tiene como objetivo ayudar" +
                            " a las mascotas a encontrar amigos y reducir su estrés durante los paseos diarios." +
                            "\n\n" +
                            "Nuestra aplicación de Android se llama \"TinPet\" y fue desarrollada después de que" +
                            " nos dimos cuenta de que muchas veces las mascotas, al igual que las personas," +
                            " necesitan socializar y hacer amigos. Las mascotas también pueden sufrir de estrés" +
                            " y aburrimiento al no tener con quién interactuar en sus paseos diarios," +
                            " lo que puede llevar a comportamientos no deseados en el hogar." +
                            " Con nuestra aplicación, queremos cambiar eso y ayudar a las mascotas" +
                            " a conectarse entre sí y hacer nuevos amigos." +
                            "\n\n" +
                            "¿Cómo funciona TinPet? Es muy sencillo. Los dueños de mascotas pueden crear" +
                            " perfiles de sus mascotas y agregar información como su nombre, edad, raza y personalidad." +
                            " También pueden agregar fotos y videos para mostrar la personalidad de sus mascotas." +
                            " Luego, pueden buscar en la aplicación otros perfiles de mascotas que estén cerca de ellos" +
                            " y que compartan intereses similares. Una vez que encuentren una mascota compatible," +
                            " pueden enviar un mensaje al dueño de esa mascota para establecer una cita de juego" +
                            " y permitir que sus mascotas se conozcan y hagan amistad." +
                            "\n\n" +
                            "Además, nuestra aplicación también ofrece una función de \"chat\" para que los dueños" +
                            " de mascotas puedan mantenerse en contacto y organizar futuras citas de juego." +
                            " La aplicación también tiene una función de \"calificación\" para que los dueños" +
                            " de mascotas puedan dejar comentarios sobre las citas de juego y compartir" +
                            " su experiencia con otros dueños de mascotas en la aplicación." +
                            "\n\n" +
                            "¿Quiénes somos nosotros, los creadores de TinPet? Somos dos estudiantes de informática, Andrés y Rodrigo," +
                            " apasionados por el mundo de las mascotas. Nos conocimos en la universidad y nos dimos cuenta de que compartimos" +
                            " una visión común: queríamos crear algo que ayudara a las mascotas a tener una vida más feliz y saludable." +
                            " Después de muchos meses de trabajo duro, lanzamos TinPet, nuestra aplicación móvil que conecta a mascotas" +
                            " de todo el mundo y ayuda a reducir el estrés en los paseos diarios." +
                            "\n\n" +
                            "Nos enorgullece decir que TinPet ha sido bien recibida por los dueños de mascotas de todo el mundo." +
                            " Nos encanta ver cómo nuestras mascotas y las de otros dueños hacen amistades gracias a nuestra aplicación." +
                            " También nos encanta escuchar las historias de éxito de nuestros usuarios, como mascotas que se han conocido" +
                            " a través de TinPet y ahora son inseparables." +
                            "\n\n" +
                            "En resumen, TinPet es una aplicación móvil diseñada para ayudar a las mascotas a hacer amigos y reducir" +
                            " el estrés en los paseos diarios. Somos dos estudiantes de informática apasionados por el mundo de" +
                            " las mascotas que queremos hacer una diferencia en la vida de las mascotas de todo el mundo." +
                            " Esperamos que disfrutes usando TinPet tanto como nosotros disfrutamos creándola." +
                            " ¡Únete a nuestra comunidad de TinPet hoy!" +
                            "\n\n"
                )
                //endregion
                //region REGION EQUIPO
                //TITULO
                Text(text = "El equipo detrás de " + stringResource(R.string.app_name),
                    fontFamily = abrilFatface,
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.onBackground
                )
                // DESARROLLADORES
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                    // DESARROLLADOR 1
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        // FOTO PERFIL
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp),
                            painter = painterResource(id = R.drawable.default_pet_5),
                            contentDescription = null
                        )
                        // NOMBRE
                        Text(text = "Andrés González", fontSize = 20.sp)
                        // APODO
                        Text(text = "Radamanes", fontSize = 10.sp)
                        Spacer(modifier=Modifier.padding(5.dp))
                        // PUESTO
                        Text(text = "BACKEND DEVELOPER", fontSize = 10.sp, fontFamily = Montserrat)
                        Spacer(modifier=Modifier.padding(5.dp))
                        // LINKS
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                tint = Color.Red,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Whatsapp,
                                tint = Color.Green,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Share,
                                tint = Color.Cyan,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Facebook,
                                tint = Color.Blue,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier=Modifier.padding(16.dp))
                    //DESARROLLADOR 2
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        // FOTO PERFIL
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp),
                            painter = painterResource(id = R.drawable.default_pet_2),
                            contentDescription = null
                        )
                        // NOMBRE
                        Text(text = "Rodrigo Domínguez", fontSize = 20.sp)
                        // APODO
                        Text(text = "LambdaR", fontSize = 10.sp)
                        Spacer(modifier=Modifier.padding(5.dp))
                        // PUESTO
                        Text(text = "FRONTEND DEVELOPER", fontSize = 10.sp, fontFamily = Montserrat)
                        Spacer(modifier=Modifier.padding(5.dp))
                        // LINKS
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                tint = Color.Red,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.RssFeed,
                                tint = Color.Magenta,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Share,
                                tint = Color.Cyan,
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.Facebook,
                                tint = Color.Blue,
                                contentDescription = null
                            )
                        }
                    }
                }
                //endregion
                Spacer(modifier=Modifier.padding(16.dp))
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
            stringResource(
                R.string.app_name
            ) + "© 2023",
            fontFamily = abrilFatface
        )
    }
}
