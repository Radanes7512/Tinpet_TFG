package com.example.tinpet.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(){

   /* val notification = rememberSaveable{mutableSetOf("")}
    if (notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }*/



   /* Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Text(
                text = "Cancelar",
                modifier = Modifier.clickable { notification.value = "Cancelado" })
            Text(
                text = "Guardar",
                modifier = Modifier.clickable { notification.value = "Actualizado" })

        }
    }*/
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}