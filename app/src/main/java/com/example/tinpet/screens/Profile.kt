package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    elevation = ButtonDefaults.elevation(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Ajustes",
                        color = MaterialTheme.colors.onPrimary
                    )

                }
            }
        }
           item {
               Row(
                   horizontalArrangement = Arrangement.Center,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(16.dp)) {
                   // Imagen de perfil del usuario
                   Image(
                       /* modifier = Modifier
                    .fillMaxWidth(),*/
                       painter = painterResource(R.drawable.profile_default_image),
                       contentDescription = "Icon profile"

                   )
               }
           }
           item{
               // MIS MASCOTAS
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(15.dp),
                   elevation = 10.dp

               ) {
                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier
                           .fillMaxSize()
                           .background(MaterialTheme.colors.primary)
                           .padding(10.dp)
                           .clickable {  }
                   ) {
                       Text(
                           modifier = Modifier
                               .align(alignment = Alignment.CenterVertically),
                           text = "Mis mascotas",
                           fontSize = 15.sp,
                           fontFamily = abrilFatface,
                           color = MaterialTheme.colors.onPrimary
                       )
                       Image(
                           modifier = Modifier
                               //.align(Alignment(0,0,))
                               //.fillMaxWidth()
                               .size(25.dp, 25.dp),
                           painter = painterResource(R.drawable.pawprint),
                           contentDescription = null,
                           alignment = Alignment.Center
                       )
                   }
               }
               // PETICIONES DE AMISTAD
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(15.dp),
                   elevation = 10.dp

               ) {
                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween,

                       modifier = Modifier
                           .clickable {  }
                           //.border(1.dp, MaterialTheme.colors.onBackground)
                           .fillMaxWidth()
                           .background(MaterialTheme.colors.primary)
                           .padding(10.dp)
                   ) {
                       Text(
                           modifier = Modifier
                               .align(alignment = Alignment.CenterVertically),
                           text = "Peticiones de amistad",
                           fontSize = 15.sp,
                           fontFamily = abrilFatface,
                           color = MaterialTheme.colors.onPrimary
                       )
                       Image(
                           modifier = Modifier
                               .size(25.dp, 25.dp),
                           painter = painterResource(R.drawable.people),
                           contentDescription = null,
                           alignment = Alignment.Center
                       )
                   }
               }
               // NOTIFICACIONES
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(15.dp),
                   elevation = 10.dp

               ) {
                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween,

                       modifier = Modifier
                           .clickable {  }
                           //.border(1.dp, MaterialTheme.colors.onBackground)
                           .fillMaxWidth()
                           .background(MaterialTheme.colors.primary)
                           .padding(10.dp)
                   ) {
                       Text(
                           modifier = Modifier
                               .align(alignment = Alignment.CenterVertically),
                           text = "Notifiaciones",
                           fontSize = 15.sp,
                           fontFamily = abrilFatface,
                           color = MaterialTheme.colors.onPrimary
                       )
                       Image(
                           modifier = Modifier
                               .size(25.dp, 25.dp),
                           painter = painterResource(R.drawable.notification),
                           contentDescription = null,
                           alignment = Alignment.Center
                       )
                   }
               }
               // SOBRE NOSOTROS
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(15.dp),
                   elevation = 10.dp

               ) {
                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier
                           .clickable {  }
                           //.border(1.dp, MaterialTheme.colors.onBackground)
                           .fillMaxWidth()
                           .background(MaterialTheme.colors.primary)
                           .padding(10.dp)

                   ) {
                       Text(
                           modifier = Modifier
                               .align(alignment = Alignment.CenterVertically),
                           text = "Sobre nosotros",
                           fontSize = 15.sp,
                           fontFamily = abrilFatface,
                           color = MaterialTheme.colors.onPrimary
                       )
                       Image(
                           modifier = Modifier
                               .size(25.dp, 25.dp),
                           painter = painterResource(R.drawable.information_button),
                           contentDescription = null,
                           alignment = Alignment.Center
                       )
                   }
               }
               // CERRAR SESIÓN
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(15.dp),
                   elevation = 10.dp

               ) {
                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier
                           .clickable {  }
                           //.border(1.dp, MaterialTheme.colors.onBackground)
                           .fillMaxWidth()
                           .background(MaterialTheme.colors.primary)
                           .padding(10.dp)
                   ) {
                       Text(
                           modifier = Modifier
                               .align(alignment = Alignment.CenterVertically),
                           text = "Cerrar sesión",
                           fontSize = 15.sp,
                           fontFamily = abrilFatface,
                           color = MaterialTheme.colors.onPrimary
                       )
                       Image(
                           modifier = Modifier
                               .size(25.dp, 25.dp),
                           painter = painterResource(R.drawable.logout),
                           contentDescription = null,
                           alignment = Alignment.Center
                       )
                   }
               }
           }

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