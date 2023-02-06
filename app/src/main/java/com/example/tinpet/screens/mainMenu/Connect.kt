package com.example.tinpet.screens.mainMenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun ConnectScreen(){
    val connectInfo = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.icon_pawprint),
            contentDescription = null
        )
        Text(
            style = TextStyle(
                shadow = Shadow(
                    color = Color.DarkGray,
                    offset = Offset(2.0f, 5.0f),
                    blurRadius = 2f
                )
            ),
            text = "Próximamente...",
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )


        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 100.dp)
                .align(Alignment.BottomCenter),
            onClick = { connectInfo.value = true }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = null
                )
                Text(text = "info")
            }

        }
    }
    if (connectInfo.value){
        AlertDialog(
            onDismissRequest = {
                connectInfo.value = false
            },
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primaryVariant),
                    contentAlignment = Alignment.Center
                    //horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        painter = if (isSystemInDarkTheme()) {
                            painterResource(R.drawable.icon_pawprint_black)
                        } else {
                            painterResource(R.drawable.icon_pawprint_white)
                        },
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            },
            text = {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Nuevas funcionalidades se acercan a TinPet.",
                    fontSize = 20.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        shape = RoundedCornerShape(size = 30.dp),
                        onClick = { connectInfo.value = false }
                    ) {
                        Text(
                            text = "Cerrar",
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }

            }
        )
    }
}


@Composable
@Preview
fun ConnectPreviewDT() {
    TinPetTheme(darkTheme = false) {
        ConnectScreen()
    }
}
@Composable
@Preview
fun ConnectPreviewLT() {
    TinPetTheme(darkTheme = true) {
        ConnectScreen()
    }
}