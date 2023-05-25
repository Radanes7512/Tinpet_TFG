package com.example.tinpet.screens.mainMenu

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.example.tinpet.MainActivity
//import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.ChatViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnspecifiedImmutableFlag")
@Composable
fun ChatScreen(
    chatUserId: String,
    message: String = "",
    viewModel: ChatViewModel,
    onBackClick: () -> Unit,
    onPetClick: () -> Unit
) {
    val context = LocalContext.current

    viewModel.getChat(chatUserId)
    viewModel.chatId.value?.let { viewModel.getMessages(it) }

    // Guardar el estado de la lista
    val listState = rememberLazyListState()
    val messages: List<Map<String, String>> by viewModel.messagesState.observeAsState(emptyList<Map<String, String>>().toMutableList())
    val message: String by viewModel.message.observeAsState("")

    // Agregar un mensaje
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
                            Text(
                                text = "Chat",
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
                    reverseLayout = false ,
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
                        ){
                            Column() {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(// SI MENSAJES SON PARES
                                            if (viewModel.isCurrentUserMessage(msg)) {// Y EL MODO OSCURO ESTA ACTIVADO
                                                if (isSystemInDarkTheme()) {// FONDO GRIS
                                                    MaterialTheme.colors.secondary
                                                } else {// SINO FONDO "onBackground"
                                                    MaterialTheme.colors.secondary
                                                }// SI MENSAJES IMPARES
                                            } else {// Y EL MODO OSCURO ESTA ACTIVADO
                                                if (isSystemInDarkTheme()) {// FONDO GRIS CLARITO
                                                    MaterialTheme.colors.primary
                                                } else {// SINO FONDO "background"
                                                    MaterialTheme.colors.surface
                                                }
                                            }
                                        )
                                        .padding(10.dp)
                                ) {
                                    // IMAGEN DEL USUARIO
                                    Image(
                                        modifier = Modifier
                                            .clickable { /*onPetClick()*/ }
                                            .clip(CircleShape)
                                            .size(25.dp)
                                            .align(alignment = Alignment.CenterVertically),
                                        painter = painterResource(R.drawable.default_pet_4),
                                        contentDescription = null
                                    )
                                    // MENSAJE
                                    msg.get("message")?.let { it1 ->
                                        addMessage()
                                        if(!viewModel.isCurrentUserMessage(msg)){
                                            //region NOTIFICACION
                                            val intent = Intent(context, MainActivity::class.java)
                                            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
                                            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                                            val channelId = "Chat Notifications"
                                            val channelName = "Chat Notifications"
                                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                                notificationManager.createNotificationChannel(
                                                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                                                )
                                            }
                                            val notification = NotificationCompat.Builder(context, channelId)
                                                .setContentTitle("Nuevo mensaje")
                                                .setContentText("Tienes un nuevo mensaje en el chat")
                                                .setSmallIcon(R.drawable.icon_pawprint)
                                                .addAction(R.drawable.icon_pawprint,"Abrir aplicación",pendingIntent)
                                                .setAutoCancel(true)
                                                .build()
                                            notificationManager.notify(0,notification)
                                            //endregion
                                        }
                                        Text(
                                            text = it1,
                                            color = MaterialTheme.colors.onBackground,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                                //region FECHA Y HORA DEL MENSAJE
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            if (viewModel.isCurrentUserMessage(msg)) {// Y EL MODO OSCURO ESTA ACTIVADO
                                                if (isSystemInDarkTheme()) {// FONDO GRIS
                                                    MaterialTheme.colors.secondary
                                                } else {// SINO FONDO "onBackground"
                                                    MaterialTheme.colors.secondary
                                                }// SI MENSAJES IMPARES
                                            } else {// Y EL MODO OSCURO ESTA ACTIVADO
                                                if (isSystemInDarkTheme()) {// FONDO GRIS CLARITO
                                                    MaterialTheme.colors.primary
                                                } else {// SINO FONDO "background"
                                                    MaterialTheme.colors.surface
                                                }
                                            }
                                        )
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
                                //endregion
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
                                Toast.makeText(context, "¡Mensaje vacío!", Toast.LENGTH_SHORT).show()
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