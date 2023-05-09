package com.example.tinpet.screens.mainMenu

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tinpet.AppScreens
import com.example.tinpet.R
import com.example.tinpet.screens.LoginViewModel
import com.example.tinpet.ui.theme.abrilFatface


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatUsersScreen(
    navController: NavHostController,
    viewModel: ChatViewModel,
) {
    //region LISTA DE FOTOS DE PERROS ( DE MOMENTO LAS ALMACENAMOS ASÍ)
    val images = listOf(
        R.drawable.default_pet,
        R.drawable.default_pet_2,
        R.drawable.default_pet_3,
        R.drawable.default_pet_4,
        R.drawable.default_pet_5
    )
    val firstImage: Int = images.toIntArray()[0]
//endregion
    //region LISTA DE NOMBRE  ( DE MOMENTO ASÍ)


    val users by viewModel.usernames.observeAsState(listOf())

    //val names = listOf(

    //viewModel.usernames.value
    //)
    //endregion
    //region CUERPO DE LA PANTALLA
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { val chatUserId = user.get("id")
                                    navController.navigate(AppScreens.Chat.route + "/${chatUserId}")}
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.primary)
                                .padding(10.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp),
                                painter = painterResource(firstImage),
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
                //endregion
            }
        }
    }
    //endregion
}

