package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
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

@Composable
fun ProfileScreen(){
    val AbrilFatface = FontFamily(
        Font(
            R.font.abril_fatface_regular
        )
    )
  Box(
       modifier = Modifier
           .fillMaxSize()
           .background(Color.White),
       contentAlignment = Alignment.Center
   ){
       LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Nombre del usuario
                    Text(
                        modifier = Modifier

                            .padding(5.dp),
                        textAlign = TextAlign.Left,
                        text = (stringResource(R.string.user_name)),
                        //text = "Test Text"
                        fontSize = 32.sp,
                        fontFamily = AbrilFatface,
                    )
                    // Botón de Ajustes
                    Button(
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = "Ajustes")

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
fun ProfileScreenPreview() {
    ProfileScreen()
}