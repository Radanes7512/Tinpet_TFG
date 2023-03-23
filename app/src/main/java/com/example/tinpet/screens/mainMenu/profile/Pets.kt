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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PetsScreen(
    onBackClick: () -> Unit,
    onAddClick:()->Unit
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
        floatingActionButton = {
           ExtendedFloatingActionButton(
                text={
                     Text(
                         text = "Añadir mascota",
                         fontFamily = abrilFatface
                     )
                },
               icon = {
                   Icon(
                       imageVector = Icons.Default.Add,
                       contentDescription = "Añadir"
                   )
               },
                onClick = {
                    onAddClick()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { }
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Max".uppercase(),
                            fontSize = 32.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier
                                //.align(Alignment(0,0,))
                                //.fillMaxWidth()
                                .clip(CircleShape)
                                .size(50.dp)
                                .align(alignment = Alignment.CenterVertically),
                            painter = painterResource(R.drawable.default_pet),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}