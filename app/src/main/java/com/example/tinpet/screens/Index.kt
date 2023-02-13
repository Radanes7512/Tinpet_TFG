package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun IndexScreen(
    onLoginClick:() -> Unit,
    onSignupClick:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LOGO SUPERIOR
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primaryVariant)
        ) {
            // Logo TinPet
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
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
        }
        Column(
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onLoginClick() },
                enabled = true,
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    disabledBackgroundColor = MaterialTheme.colors.onSurface,
                    contentColor = Color.Black,
                    disabledContentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Login,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(R.string.go_to_login_ES)
                )
            }
            Button(
                onClick = { onSignupClick() },
                enabled = true,
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    disabledBackgroundColor = MaterialTheme.colors.onSurface,
                    contentColor = Color.Black,
                    disabledContentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.AppRegistration,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(R.string.go_to_register_ES)
                )
            }
        }
    }
}
@Composable
@Preview (showBackground = true, showSystemUi = true)
fun IndexScreenPreview(){
    IndexScreen(onLoginClick = {}, onSignupClick = {})
}