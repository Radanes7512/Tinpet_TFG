package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun ProfileScreen(){
   Box(
       modifier = Modifier
           .fillMaxSize()
           .background(color = MaterialTheme.colors.background),
       contentAlignment = Alignment.Center
   ){
       LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Nombre del usuario
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        text = (stringResource(R.string.user_name)),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                    // Botón de Ajustes
                    Button(
                        elevation = ButtonDefaults.elevation(),
                        onClick = { /*TODO*/ }
                    ) {
                        Surface(
                            color = MaterialTheme.colors.secondary,
                            contentColor = MaterialTheme.colors.secondary
                        ) {

                        }
                        Text(
                            text = "Ajustes",
                            color = MaterialTheme.colors.secondary
                        )

                    }
            }
            // Imagen de perfil del usuario
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.profile_default_image),
                contentDescription = "Icon profile"

            )
        }
    }
       LazyColumn(
           modifier = Modifier
               .fillMaxWidth()
               .background(Color.Red)
       ) {

       }

   }

}

@Composable
@Preview
fun ProfileScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        ProfileScreen()
    }
}
@Composable
@Preview
fun ProfileScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        ProfileScreen()
    }
}