package com.example.tinpet.screens.mainMenu.profile

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.tinpet.R
import com.example.tinpet.screens.Constants
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.RequestsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RequestScreen(
    onBackClick: () -> Unit,
    onPetClick: () -> Unit,
    viewModel: RequestsViewModel
){
    viewModel.CheckFriendRequests()

    val FriendsRequests: List<Map<String, String>> by viewModel.FriendList.observeAsState(emptyList<Map<String, String>>().toMutableList())

    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Peticiones",
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
    // region CUERPO
    {
        if (FriendsRequests.isNullOrEmpty()){
            EndBox()
        }else{
            LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
               itemsIndexed(FriendsRequests) { index, fr ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        elevation = 10.dp

                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .clickable { onPetClick() }
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.primary)
                                .padding(10.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(16.dp, 0.dp)
                                    .clip(CircleShape)
                                    .size(25.dp, 25.dp)
                                    .align(alignment = Alignment.CenterVertically),
                                painter = painterResource(R.drawable.default_pet_3),
                                contentDescription = null,
                            )
                            fr[Constants.PET_NAME].let { it1 ->
                                if (it1 != null) {
                                    Text(
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                                        text = it1,
                                        fontSize = 20.sp,
                                        fontFamily = abrilFatface,
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(ButtonDefaults.IconSize)
                                    .clickable { viewModel.onFriendRequestUpdate(fr, Constants.DECLINED)}
                            )
                            Text(
                                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                                text = " | ",
                                fontSize = 20.sp,
                                fontFamily = abrilFatface,
                                color = MaterialTheme.colors.onPrimary
                            )
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(ButtonDefaults.IconSize)
                                    .clickable { viewModel.onFriendRequestUpdate(fr, Constants.ACCEPTED)}
                            )
                        }
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
                text = "No tienes peticiones de amistad, aun no se han dado cuenta de lo preciosa que es tu mascota.",
                fontWeight = FontWeight.Bold
            )
        }
        }
    }
