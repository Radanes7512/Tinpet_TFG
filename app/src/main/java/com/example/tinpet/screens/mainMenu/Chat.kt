package com.example.tinpet.screens.mainMenu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Botón de atrás
            Button(
                onClick = { onBackClick() },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
            Text(
                text = "PERRITO",
                fontSize = 32.sp,
                fontFamily = abrilFatface,
                color = MaterialTheme.colors.onBackground
            )
        }

        // Box para mostrar el texto enviado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Texto enviado
            Text(
                text = viewModel.messages.value.toString(),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
        ) {
            // Cuadro de texto
            val textState = remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.weight(1f),
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Escribir mensaje...") }
            )
            // Botón de enviar
            Button(
                onClick = {
                    viewModel.sendMessage(textState.value)
                    textState.value = ""
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null
                )
            }
        }
    }
}
