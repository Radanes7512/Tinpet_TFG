package com.example.tinpet.screens.mainMenu

//import androidx.core.content.ContentProviderCompat.requireContext
import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.ChatViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    chatUserId: String,
    viewModel: ChatViewModel,
    onBackClick: () -> Unit,
) {
    viewModel.getChat(chatUserId)
    viewModel.chatId.value?.let { viewModel.getMessages(it) }
    val context = LocalContext.current

    // Guardar el estado de la lista
    val listState = rememberLazyListState()

    val users by viewModel.usernames.observeAsState(listOf())
    val messages: List<Map<String, String>> by viewModel.messagesState.observeAsState(emptyList<Map<String, String>>().toMutableList())
    val message: String by viewModel.message.observeAsState("")

    @Composable
    fun addMessage() {
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
                    users.forEach { user ->
                        user.get("name")?.let {
                            Text(
                                text = it,
                                fontFamily = abrilFatface
                            )
                        }
                    }
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
                    reverseLayout = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    itemsIndexed(messages) { index, msg ->
                        Card(
                            modifier = Modifier
                                .padding(
                                    if (viewModel.isCurrentUserMessage(msg)) PaddingValues(
                                        50.dp,
                                        5.dp,
                                        5.dp,
                                        0.dp
                                    ) else PaddingValues(5.dp, 5.dp, 50.dp, 0.dp)
                                )
                                .heightIn(min = 48.dp, max = Float.POSITIVE_INFINITY.dp)
                        ) {
                            Column {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            if (viewModel.isCurrentUserMessage(msg)) {
                                                if (isSystemInDarkTheme()) {
                                                    MaterialTheme.colors.secondary
                                                } else {
                                                    MaterialTheme.colors.secondary
                                                }
                                            } else {
                                                if (isSystemInDarkTheme()) {
                                                    MaterialTheme.colors.primary
                                                } else {
                                                    MaterialTheme.colors.surface
                                                }
                                            }
                                        )
                                        .padding(10.dp)
                                ) {
                                    // MENSAJE
                                    msg.get("message")?.let { it1 ->
                                        addMessage()
                                        Text(
                                            text = it1,
                                            color = MaterialTheme.colors.onBackground,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                                // FECHA Y HORA DEL MENSAJE
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            if (viewModel.isCurrentUserMessage(msg)) {
                                                if (isSystemInDarkTheme()) {
                                                    MaterialTheme.colors.secondary
                                                } else {
                                                    MaterialTheme.colors.secondary
                                                }
                                            } else {
                                                if (isSystemInDarkTheme()) {
                                                    MaterialTheme.colors.primary
                                                } else {
                                                    MaterialTheme.colors.surface
                                                }
                                            }
                                        )
                                        .padding(10.dp)
                                ) {
                                    val calendar = Calendar.getInstance()
                                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                                    val minute = calendar.get(Calendar.MINUTE)
                                    Text(
                                        text = "${hour}:${String.format("%02d", minute)}"
                                    )
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
                        modifier = Modifier
                            .weight(1f),
                        shape = CutCornerShape(5.dp),
                        value = message,
                        onValueChange = { viewModel.updateMessage(it) },
                        label = { Text("Escribir mensaje...") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.onBackground,
                            unfocusedBorderColor = Color.Gray
                        )
                    )
                    Button(
                        onClick = {
                            if (message.isNotBlank()) {
                                viewModel.sendMessage(message)
                                viewModel.updateMessage("")
                            } else {
                                Toast.makeText(context, "¡Mensaje vacío!", Toast.LENGTH_SHORT)
                                    .show()
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