package com.example.tinpet.screens.mainMenu.profile.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.BottomBar2
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
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
        ) {
            item {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "    La historia detrás de la creación de la aplicación: " +
                                "Puedes contar cómo surgió la idea de crear la aplicación, por qué los desarrolladores decidieron crearla " +
                                "y cómo creen que puede ayudar a los dueños de perros.\n" +
                                "\n" +
                                "    Información sobre los desarrolladores: " +
                                "Puedes incluir una breve biografía de los desarrolladores, sus intereses, hobbies y cómo se relacionan con" +
                                " el mundo de los perros.\n" +
                                "\n" +
                                "    Los valores de la empresa: " +
                                "Puedes hablar sobre los valores de la empresa, como la importancia del bienestar animal, la inclusión de todas" +
                                " las razas de perros y la promoción de la sociabilidad y el ejercicio.\n" +
                                "\n" +
                                "    Estadísticas sobre la falta de sociabilidad entre perros:" +
                                " Puedes incluir estadísticas que muestren la falta de sociabilidad entre perros y cómo la aplicación puede ayudar" +
                                " a cambiar esa situación.\n" +
                                "\n" +
                                "    Funcionalidades de la aplicación:" +
                                " Puedes explicar las funcionalidades de la aplicación en detalle, cómo funciona, cómo los usuarios pueden registrarse" +
                                " y empezar a buscar citas para sus perros, y cómo se asegura la seguridad de los usuarios.\n" +
                                "\n" +
                                "    Testimonios de usuarios:" +
                                " Puedes incluir algunos testimonios de usuarios que han utilizado la aplicación y han tenido éxito en entablar citas para" +
                                " sus perros, y cómo ha mejorado la calidad de vida de sus perros y su propia experiencia como dueños de perros."
                    )
                }

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
