package com.example.tinpet.screens.mainMenu

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.screens.Constants
import com.example.tinpet.ui.theme.abrilFatface


@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    viewModel.getUserPets()
    viewModel.getLoggedUser()

    val userPets: List<Map<String, String>> by viewModel.UserPets.observeAsState(emptyList<Map<String, String>>().toMutableList())

    // LISTA DE FOTOS DE PERROS ( DE MOMENTO LAS ALMACENAMOS ASÍ)
    val images = listOf(
        R.drawable.default_pet,
        R.drawable.default_pet_2,
        R.drawable.default_pet_3,
        R.drawable.default_pet_4,
        R.drawable.default_pet_5
    )

    val category = listOf(
        stringResource(R.string.peaceful_ES),
        stringResource(R.string.playful_ES),
        stringResource(R.string.eater_ES),
        stringResource(R.string.sleepyhead_ES),
        stringResource(R.string.nervous_ES),
        stringResource(R.string.aggressive_ES),
        stringResource(R.string.protective_ES),
        stringResource(R.string.loyal_ES),
        stringResource(R.string.affectionate_ES),
        stringResource(R.string.intelligent_ES),
        stringResource(R.string.obedient_ES),
        stringResource(R.string.curious_ES)
    )

    var currentIndex by remember { mutableStateOf(0) }
    var petLiked by remember { mutableStateOf(false) }
    var petDisliked by remember { mutableStateOf(false) }
    var showEndBox by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(0.dp) }

    // LÓGICA PARA QUE CUANDO NO HAYA MÁS IMÁGENES PARA MOSTAR, SE VEA EL BOX DE FIN
    fun onButtonClick(liked: Boolean) {
        if(liked){
            currentIndex++
            if (currentIndex == userPets.size || currentIndex ==images.size) {
                showEndBox = true
            }
        }else{
            currentIndex++
            if (currentIndex == userPets.size || currentIndex ==images.size) {
                showEndBox = true
            }
        }
    }

    if (showEndBox) {
        EndBox()
    } else {
        if (userPets.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 16.dp, 16.dp, 100.dp)
            ) {
                val imageRes = images[currentIndex]
                val petname = userPets[currentIndex][Constants.PET_NAME].toString()
                val petage = userPets[currentIndex][Constants.PET_AGE].toString()
                if (petLiked) {
                    LaunchedEffect(Unit) {
                        animate(
                            initialValue = 0f,
                            targetValue = 100f,
                            animationSpec = tween(durationMillis = 3000)
                        ) { value, _ ->
                            offsetX = value.dp
                        }
                    }
                    petLiked = false
                } else if (petDisliked) {
                    LaunchedEffect(Unit) {
                        animate(
                            initialValue = 0f,
                            targetValue = -100f,
                            animationSpec = tween(durationMillis = 3000)
                        ) { value, _ ->
                            offsetX = value.dp
                        }
                    }
                    petDisliked = false
                }
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "My Image",
                    modifier = Modifier
                        .offset(
                            x = offsetX,
                            y = 0.dp
                        )
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                    //  .background(Color(0x80000000))
                ) {
                    if (petname != null) {
                        Text(
                            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 5.dp),
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.DarkGray,
                                    offset = Offset(2.0f, 5.0f),
                                    blurRadius = 2f
                                )
                            ),
                            text = petname.uppercase(),
                            fontSize = 32.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    if (petage != null) {
                        Text(
                            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.DarkGray, offset = Offset(2.0f, 5.0f), blurRadius = 2f
                                )
                            ),
                            text = "$petage años",
                            fontSize = 22.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    // CATEGORÍAS
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        category.shuffled().take(1).forEach { category ->
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
                            .clickable {
                                petDisliked = true
                                onButtonClick(false)
                            }
                            .size(70.dp)
                            .padding(bottom = 16.dp),
                            painter = painterResource(id = R.drawable.icon_notlike),
                            contentDescription = "Dislike Button",
                            colorFilter = if (petDisliked) ColorFilter.tint(Color.Red) else null
                        )
                        // LIKE (DERECHA)
                        Image(modifier = Modifier
                            .clickable {
                                viewModel.SendFriendRequests(userPets[currentIndex][Constants.EMAIL])
                                petLiked = true
                                onButtonClick(true)
                            }
                            .size(70.dp)
                            .padding(bottom = 16.dp),
                            painter = painterResource(id = R.drawable.icon_like),
                            contentDescription = "Like Button",
                            colorFilter = if (petLiked) ColorFilter.tint(Color.Green) else null
                        )
                    }
                }
            }
        }
    }


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
            )
        }
    }
}
