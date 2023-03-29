package com.example.tinpet.screens.mainMenu

import android.annotation.SuppressLint
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tinpet.R
import com.example.tinpet.screens.LoginViewModel
import com.example.tinpet.ui.theme.abrilFatface
import com.google.type.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration.Companion.days

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    onBackClick: () -> Unit,
    onPetClick: () -> Unit

) {
    // Guardar el estado de la lista
    val listState = rememberLazyListState()
    val userName = viewModel.selectedUserName ?: ""

    val message: String by viewModel.message.observeAsState("")

    //Mensajes actualizados a mostrar
    val messages:
            List<Map<String, Any>> by viewModel.messages.observeAsState(emptyList<Map<String, Any>>().toMutableList())

    // Agregar un mensaje
    @Composable
    fun addMessage() {
        // Tu código para agregar el mensaje

        // Después de agregar el mensaje, animar el scroll hasta el último elemento de la lista
        LaunchedEffect(Unit) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE DE MASCOTA Y FELCHA PARA IR ATRÁS
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = userName,
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
        //endregion
        // region CUERPO DEL CHAT DONDE SE VAN AÑADIENDO LOS MENSAJES ENVIADOS
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                LazyColumn(
                    state = listState,
                    reverseLayout = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    itemsIndexed(messages) { index, message ->
                        Card(
                            modifier = Modifier
                                .padding(
                                    if (index % 2 == 0) PaddingValues(
                                        50.dp,
                                        5.dp,
                                        5.dp,
                                        0.dp
                                    ) else PaddingValues(5.dp, 5.dp, 50.dp, 0.dp)
                                )
                        )
                        {
                            Column() {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(if (index % 2 == 0) Color.Gray else Color.LightGray)
                                        .padding(10.dp)
                                ) {
                                    // IMAGEN DEL USUARIO
                                    Image(
                                        modifier = Modifier
                                            .clickable { onPetClick() }
                                            .clip(CircleShape)
                                            .size(25.dp)
                                            .align(alignment = Alignment.CenterVertically),
                                        painter = painterResource(R.drawable.default_pet_4),
                                        contentDescription = null
                                    )
                                    // MENSAJE
                                    Text(
                                        text = message.values.toString(),
                                        color = MaterialTheme.colors.onBackground,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                                // FECHA Y HORA DEL MENSAJE
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(if (index % 2 == 0) Color.Gray else Color.LightGray)
                                        .padding(10.dp)
                                ){
                                    // OPCION 1
                                    val calendar = Calendar.getInstance()
                                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                                    val minute = calendar.get(Calendar.MINUTE)
                                    Text(
                                        text = "${hour}:${String.format("%02d", minute)}"
                                    )
                                    // OPCION 2
                                    /*
                                    val now = LocalDateTime.now()
                                    val formatter = DateTimeFormatter.ofPattern("HH:mm")
                                    Text(
                                        text = now.format(formatter)
                                     )
                                     */
                                }
                            }
                        }
                    }
                }
                //region TEXT FIELD PARA ESCRIBIR Y BOTON PARA ENVIAR EL MENSAJE
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = message,
                        onValueChange = { viewModel.updateMessage(it) },
                        label = { Text("Escribir mensaje...") }
                    )
                    Button(
                        onClick = {
                            if (message.isNotBlank()) {
                                viewModel.addMessage()
                            }
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Icon(
                            Icons.Filled.Send,
                            contentDescription = null
                        )
                    }
                }
                //endregion
            }
        }
        //endregion
    )
}