package com.example.tinpet.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tinpet.R
import com.example.tinpet.homeComponents.CardStack
import com.example.tinpet.homeComponents.Item
import com.example.tinpet.ui.theme.TinPetTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val isEmpty = remember {mutableStateOf(false)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            // IMAGEN MASCOTA
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (!isEmpty.value) {
                        CardStack(
                            items = accounts,
                            onEmptyStack = {
                                isEmpty.value = true
                            }
                        )
                    } else {
                        Text(
                            text = "¡Ups! No hay más mascotas cerca por el momento",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }/*
            // CATEGORIAS
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)

                    ) {
                        Card(
                            elevation = 10.dp
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                text = "Categoría 1",

                                )
                        }
                        Card(
                            elevation = 10.dp
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                text = "Categoría 2"
                            )
                        }
                        Card(
                            elevation = 10.dp
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                text = "Categoría 3"
                            )
                        }
                        Card(
                            modifier = Modifier
                                .size(25.dp),
                            elevation = 10.dp
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clickable { }
                                    .padding(5.dp),
                                imageVector = Icons.Filled.MoreHoriz,
                                contentDescription = null
                            )
                        }
                    }
                }

            }*/
            /*
            // OPCIONES DE LIKE / DISLIKE / STAR
            val ic_size = 70.dp
            val ic_star_size = 50.dp
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .clickable { }
                            .size(ic_size, ic_size),
                        painter = painterResource(R.drawable.icon_notlike),
                        contentDescription = null,
                        alignment = Alignment.Center
                    )
                    Image(
                        modifier = Modifier
                            .clickable { }
                            .size(ic_star_size, ic_star_size),
                        painter = painterResource(R.drawable.icon_star),
                        contentDescription = null,
                        alignment = Alignment.Center
                    )
                    Image(
                        modifier = Modifier
                            .clickable { }
                            .size(ic_size, ic_size),
                        painter = painterResource(R.drawable.icon_like),
                        contentDescription = null,
                        alignment = Alignment.Center
                    )
                }
            }*/


        }
    }
}
val accounts = mutableListOf<Item>(
    Item(R.drawable.default_pet, "Max", "5 años"),
    Item(R.drawable.default_pet, "Scooby", "3 años"),
    Item(R.drawable.default_pet, "Duke", "6 años")
)
@Composable
@Preview
fun HomeScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Composable
@Preview
fun HomeScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        HomeScreen()
    }
}