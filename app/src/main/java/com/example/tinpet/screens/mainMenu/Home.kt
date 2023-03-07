package com.example.tinpet.screens.mainMenu

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
<<<<<<< HEAD
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
=======
>>>>>>> 5e378163f5f4b720cff8eed5b5d4db702008e786
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
<<<<<<< HEAD
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
=======
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
>>>>>>> 5e378163f5f4b720cff8eed5b5d4db702008e786
import com.example.tinpet.ui.theme.abrilFatface


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    // LISTA DE FOTOS DE PERROS ( DE MOMENTO LAS ALMACENAMOS ASÍ)
    val images = listOf(
        R.drawable.default_pet,
        R.drawable.default_pet_2,
        R.drawable.default_pet_3,
        R.drawable.default_pet_4,
        R.drawable.default_pet_5
    )
<<<<<<< HEAD
    // LISTA DE NOMBRE  ( DE MOMENTO ASÍ)
    val names = listOf(
        "Max", "Scooby", "Calcetines", "Brutus", "Duke"
    )
    // LISTA DE EDAD ( DE MOMENTO ASÍ)
    val ages = listOf(
        "5 años",
        "1 año",
        "3 años",
        "6 años",
        "4 años",
    )

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

    var selectedVariable by remember { mutableStateOf(0) }
    var currentIndex by remember { mutableStateOf(0) }
    var liked by remember { mutableStateOf(false) }
    var disliked by remember { mutableStateOf(false) }
    var showEndBox by remember { mutableStateOf(false) }

=======
    // LISTA DE NOMBRE Y EDAD ( DE MOMENTO ASÍ)
    val descriptions = listOf(
        "Max, 5 años",
        "Scooby, 1 año",
        "Calcetines, 3 años",
        "Brutus, 6 años",
        "Duke, 4 años"
    )

    var selectedVariable by remember { mutableStateOf(0) }
    var currentIndex by remember { mutableStateOf(0) }
    var liked by remember { mutableStateOf(false) }
    var disliked by remember { mutableStateOf(false) }
    var showEndBox by remember { mutableStateOf(false) }

>>>>>>> 5e378163f5f4b720cff8eed5b5d4db702008e786
    // LÓGICA PARA QUE CUANDO NO HAYA MÁS IMÁGENES PARA MOSTAR, SE VEA EL BOX DE FIN
    fun onButtonClick(liked: Boolean) {
        if (!liked && !disliked) {
            disliked = !liked
        } else if (liked && !disliked) {
            currentIndex++
            if (currentIndex == images.size) {
                showEndBox = true
            }
        } else if (!liked && disliked) {
            currentIndex++
            if (currentIndex == images.size) {
                showEndBox = true
            }
        }
    }

    if (showEndBox) {
        EndBox()
    } else {
        Box(
<<<<<<< HEAD
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 16.dp, 16.dp, 100.dp)
        ) {
            val imageRes = images[currentIndex]
            val petname = names[currentIndex]
            val petage = ages[currentIndex]

            Image(
                painter = painterResource(imageRes),
                contentDescription = "My Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)

                    .background(Color(0x80000000))
            ) {
                Text(
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 5.dp),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.DarkGray, offset = Offset(2.0f, 5.0f), blurRadius = 2f
                        )
                    ),
                    text = petname.uppercase(),
                    fontSize = 32.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.DarkGray, offset = Offset(2.0f, 5.0f), blurRadius = 2f
                        )
                    ),
                    text = petage,
                    fontSize = 22.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
                // CATEGORÍAS
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
                // BOTONES LIKE Y DISLIKE
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // DISLIKE (IZQUIERDA)
                    Image(modifier = Modifier
                        .clickable { }
                        .size(70.dp)
                        .padding(bottom = 16.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                if (!liked && !disliked) {
                                    disliked = true
                                    onButtonClick(false)
                                }
                            })
                        },
                        painter = painterResource(id = R.drawable.icon_notlike),
                        contentDescription = "Dislike Button",
                        colorFilter = if (disliked) ColorFilter.tint(Color.Red) else null
                    )
                    // LIKE (DERECHA)
                    Image(modifier = Modifier
                        .clickable { }
                        .size(70.dp)
                        .padding(bottom = 16.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                if (!liked && !disliked) {
                                    liked = true
                                    onButtonClick(true)
                                }
                            })
                        },
                        painter = painterResource(id = R.drawable.icon_like),
                        contentDescription = "Like Button",
                        colorFilter = if (liked) ColorFilter.tint(Color.Green) else null
                    )
                }
            }
        }
    }
    liked = false
    disliked = false
}

@Composable
fun EndBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.icon_pawprint),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.DarkGray, offset = Offset(2.0f, 5.0f), blurRadius = 2f
                    )
                ), text = "¡Ups!", fontWeight = FontWeight.Bold, fontSize = 50.sp
            )
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.DarkGray, offset = Offset(2.0f, 5.0f), blurRadius = 2f
                    )
                ), text = "No hay más mascotas cerca por el momento", fontWeight = FontWeight.Bold
=======
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 16.dp, 16.dp, 100.dp)
        ) {
            val imageRes = images[currentIndex]
            val text = descriptions[currentIndex]

            Image(
                painter = painterResource(imageRes),
                contentDescription = "My Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.DarkGray,
                            offset = Offset(2.0f, 5.0f),
                            blurRadius = 2f
                        )
                    ),
                    text = text,
                    fontSize = 32.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        modifier = Modifier
                            .clickable { }
                            .size(70.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        if (!liked && !disliked) {
                                            disliked = true
                                            onButtonClick(false)
                                        }
                                    }
                                )
                            },
                        painter = painterResource(id = R.drawable.icon_notlike),
                        contentDescription = "Dislike Button",
                        colorFilter = if (disliked) ColorFilter.tint(Color.Red) else null
                    )

                    Image(
                        modifier = Modifier
                            .clickable { }
                            .size(70.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        if (!liked && !disliked) {
                                            liked = true
                                            onButtonClick(true)
                                        }
                                    }
                                )
                            },
                        painter = painterResource(id = R.drawable.icon_like),
                        contentDescription = "Like Button",
                        colorFilter = if (liked) ColorFilter.tint(Color.Green) else null
                    )
                }
            }
        }
    }
    liked = false
    disliked = false
}
@Composable
fun EndBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.icon_pawprint),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = Offset(2.0f, 5.0f),
                        blurRadius = 2f
                    )
                ),
                text = "¡Ups!",
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp
            )
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = Offset(2.0f, 5.0f),
                        blurRadius = 2f
                    )
                ),
                text = "No hay más mascotas cerca por el momento",
                fontWeight = FontWeight.Bold
>>>>>>> 5e378163f5f4b720cff8eed5b5d4db702008e786
            )
        }
    }
}
