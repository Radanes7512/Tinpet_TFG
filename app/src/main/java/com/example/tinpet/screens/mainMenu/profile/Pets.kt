package com.example.tinpet.screens.mainMenu.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.screens.mainMenu.HomeViewModel
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PetsScreen(
    onBackClick: () -> Unit,
    onAddClick:()->Unit,
    onPetClick: () -> Unit,
    homeViewModel: HomeViewModel
){

    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis mascotas",
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
    )
    //endregion
    // region CUERPO
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
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
                                .size(25.dp,25.dp)
                                .align(alignment = Alignment.CenterVertically),
                            painter = painterResource(R.drawable.default_pet),
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = homeViewModel.loggedUserName.toString(),
                            fontSize = 20.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}
