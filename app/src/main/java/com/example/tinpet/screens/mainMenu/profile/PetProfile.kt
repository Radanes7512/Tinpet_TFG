package com.example.tinpet.screens.mainMenu.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.AppScreens
import com.example.tinpet.R
import com.example.tinpet.graphs.NavGraph
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PetProfileScreen(
    onBackClick: () -> Unit,
    onChatClick: () -> Unit,
) {
    // LISTA DE FOTOS DE PERROS ( DE MOMENTO LAS ALMACENAMOS ASÍ)
    val images = listOf(
        R.drawable.default_pet,
        R.drawable.default_pet_2,
        R.drawable.default_pet_3,
        R.drawable.default_pet_4,
        R.drawable.default_pet_5
    )

    // LISTA DE NOMBRE  ( DE MOMENTO ASÍ)
    val names = listOf("Max", "Scooby", "Calcetines", "Brutus", "Duke")

    // LISTA DE EDAD ( DE MOMENTO ASÍ)
    val ages = listOf("5 años", "1 año", "3 años", "6 años", "4 años")

    // LISTA DE CATEGORÍAS ( DE MOMENTO ASÍ)
    val category = listOf(
        "Travieso",
        "Pequeño",
        "Grande",
        "Tranquilo",
        "Juguetón",
        "Serio",
        "Comilón",
        "Dormilón",
    )
    names.shuffled().take(1).forEach { name ->
        //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {

                        Text(
                            text = "Perfil de $name",
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
        {
            //PROFILE CONTENT
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(
                            R.drawable.icon_pawprint
                        )
                    )
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .size(350.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.default_pet),
                            alpha = 0.8f,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentDescription = "profilePhoto"
                        )
                        //region IMAGEN
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                                .border(1.dp, Color.Yellow, CircleShape)
                        ) {
                            Image(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(150.dp),
                                painter = painterResource(R.drawable.default_pet),
                                contentDescription = "profilePhoto"
                            )
                        }
                        //endregion
                    }
                    //region NOMBRE Y EDAD
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment= Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Nombre de la mascota
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Nombre: $name",
                            fontSize = 20.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                        // Edad de la mascota
                            Spacer(Modifier.height(16.dp))
                        ages.shuffled().take(1).forEach { age ->
                                // Edad de la mascota
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = "Edad: $age",
                                    fontSize = 20.sp,
                                    fontFamily = abrilFatface,
                                    color = MaterialTheme.colors.onBackground
                                )
                        }
                        //endregion
                    }
                    //endregion

                }
                item {
                    //region CATEGORÍAS
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        category.shuffled().take(3).forEach { category ->
                            Card(
                                modifier = Modifier.padding(8.dp),
                                elevation = 10.dp
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = category,
                                    fontSize = 16.sp,
                                    fontFamily = abrilFatface
                                )
                            }
                        }
                    }
                    //endregion
                }
            }
        }
    }
}
