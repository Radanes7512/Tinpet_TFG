package com.example.tinpet.screens.mainMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatUsersScreen(
    onClick: () -> Unit, onBackClick: () -> Unit
) {

    // LISTA DE FOTOS DE PERROS ( DE MOMENTO LAS ALMACENAMOS ASÍ)
    val images = listOf(
        R.drawable.default_pet,
        R.drawable.default_pet_2,
        R.drawable.default_pet_3,
        R.drawable.default_pet_4,
        R.drawable.default_pet_5
    )
    val firstImage: Int = images.toIntArray()[0]

    // LISTA DE NOMBRE  ( DE MOMENTO ASÍ)
    val names = listOf(
        "Max", "Scooby", "Calcetines", "Brutus", "Duke"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                names.shuffled().take(5).forEach { names ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        elevation = 10.dp
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .clickable { onClick() }
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.primary)
                                .padding(10.dp))
                        {
                            Image(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp),
                                painter = painterResource(firstImage),
                                contentDescription = "profilePhoto"
                            )
                        }
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = names,
                            fontSize = 16.sp,
                            fontFamily = abrilFatface
                        )
                    }
                }
            }
        }
    }
}
