package com.example.tinpet.screens.mainMenu

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tinpet.AppScreens
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.ChatViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatUsersScreen(
    navController: NavHostController,
    viewModel: ChatViewModel,
) {
    val users by viewModel.usernames.observeAsState(listOf())

    Scaffold(content = {
        //region CUERPO DE LA PANTALLA
        if (users.isNullOrEmpty()) {
            EmptyChat()
        } else {
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
                        //region FUNCION PARA CREAR ENTRADAS AL CHAT DEPENDIENDO DEL NUMERO DE PERROS
                        users.forEach { user ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                elevation = 10.dp
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            val chatUserId = user.get("id")
                                            navController.navigate(AppScreens.Chat.route + "/${chatUserId}")
                                        }
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colors.primary)
                                        .padding(10.dp)) {
                                    Image(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(50.dp),
                                        painter = painterResource(R.drawable.profile_pawprint),
                                        contentDescription = "profilePhoto"
                                    )
                                    user.get("name")?.let {
                                        Text(
                                            modifier = Modifier.padding(8.dp),
                                            text = it,
                                            fontSize = 16.sp,
                                            fontFamily = abrilFatface
                                        )
                                    }
                                }

                            }
                        }
                    }
                    //endregion
                }
            }
        }
        //endregion
    })
}

@Composable
fun EmptyChat() {
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
                ), text = "No hay chats todavía...", fontWeight = FontWeight.Bold
            )
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.DarkGray, offset = Offset(2.0f, 5.0f), blurRadius = 2f
                    )
                ), text = "¡Revisa las peticiones de amistad!", fontWeight = FontWeight.Bold
            )
        }
    }
}

