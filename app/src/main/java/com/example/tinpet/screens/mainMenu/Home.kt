package com.example.tinpet.screens.mainMenu

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import coil.compose.AsyncImage
import com.example.tinpet.Constants
import com.example.tinpet.MainActivity
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.HomeViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    viewModel.getNonFriends()
    viewModel.getLoggedUser()

    val userPets: List<MutableMap<String, String>> by viewModel.UserPets.observeAsState(emptyList<MutableMap<String, String>>().toMutableList())

    // LISTA DE FOTOS DE PERROS (POR DEFECTO)
    val images = listOf(
        R.drawable.default_pet,
        R.drawable.default_pet_2,
        R.drawable.default_pet_3,
        R.drawable.default_pet_4,
        R.drawable.default_pet_5
    )

    var currentIndex by remember { mutableStateOf(0) }
    var petLiked by remember { mutableStateOf(false) }
    var petDisliked by remember { mutableStateOf(false) }
    var showEndBox by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(0.dp) }

    // LÓGICA PARA QUE CUANDO NO HAYA MÁS IMÁGENES PARA MOSTAR, SE VEA EL BOX DE FIN
    fun onButtonClick(liked: Boolean) {
        if (liked) {
            currentIndex++
            if (currentIndex == userPets.size || currentIndex == images.size) {
                showEndBox = true
            }
        } else {
            currentIndex++
            if (currentIndex == userPets.size || currentIndex == images.size) {
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
                val imageUri = userPets[currentIndex].getOrDefault(Constants.PHOTO, null)
                val petname = userPets[currentIndex][Constants.PET_NAME].toString()
                val petage = userPets[currentIndex][Constants.PET_AGE].toString()
                val petcategory = userPets[currentIndex][Constants.PET_CATEGORY].toString()
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
                if (imageUri != null) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "My Image",
                        modifier = Modifier
                            .offset(
                                x = offsetX,
                                y = 0.dp
                            )
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    var image = images.elementAt((0..4).random())
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "My Image",
                        modifier = Modifier
                            .offset(
                                x = offsetX,
                                y = 0.dp
                            )
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                if (petLiked) {
                    LaunchedEffect(Unit) {
                        animate(
                            initialValue = 0f,
                            targetValue = 100f,
                            animationSpec = tween(durationMillis = 500)
                        ) { value, _ ->
                            offsetX = value.dp
                        }
                    }
                } else if (petDisliked) {
                    LaunchedEffect(Unit) {
                        animate(
                            initialValue = 0f,
                            targetValue = -100f,
                            animationSpec = tween(durationMillis = 500)
                        ) { value, _ ->
                            offsetX = value.dp
                        }
                    }
                } else {
                    LaunchedEffect(Unit) {
                        animate(
                            initialValue = 0f,
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 500)
                        ) { value, _ ->
                            offsetX = value.dp
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(Color(0x80000000))
                ) {
                    //NOMBRE
                    if (petname != null) {
                        Text(
                            modifier = Modifier.padding(8.dp),
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
                            color = if(isSystemInDarkTheme())MaterialTheme.colors.onBackground else MaterialTheme.colors.background
                        )
                    }
                    // EDAD
                    if (petage != null) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.DarkGray,
                                    offset = Offset(2.0f, 5.0f),
                                    blurRadius = 2f
                                )
                            ),
                            text = "$petage años",
                            fontSize = 22.sp,
                            fontFamily = abrilFatface,
                            color = if(isSystemInDarkTheme())MaterialTheme.colors.onBackground else MaterialTheme.colors.background

                        )
                    }
                    // CATEGORÍAS
                    if (petcategory != null) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Card(
                                modifier = Modifier.padding(8.dp),
                                elevation = 10.dp
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = petcategory,
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
                                Toast.makeText(context, "Has pasado de $petname...", Toast.LENGTH_SHORT).show()
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
                                Toast.makeText(context, "Se ha enviado  una petición de amistad", Toast.LENGTH_SHORT).show()
                                petLiked = true
                                onButtonClick(true)
                                //region NOTIFICACION
                                val intent = Intent(context, MainActivity::class.java)
                                val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
                                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                                val channelId = "Home Notifications"
                                val channelName = "Home Notifications"
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                    notificationManager.createNotificationChannel(
                                        NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                                    )
                                }
                                val notification = NotificationCompat.Builder(context, channelId)
                                    .setContentTitle("Solicitud enviada")
                                    .setContentText("¡Has enviado una solicitud de amistad a $petname!")
                                    .setSmallIcon(R.drawable.icon_pawprint)
                                    .setAutoCancel(true)
                                    .build()
                                notificationManager.notify(0,notification)
                                //endregion
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
    petLiked = false
    petDisliked = false
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
