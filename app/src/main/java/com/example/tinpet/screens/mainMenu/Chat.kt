import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tinpet.screens.mainMenu.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        // Lista de mensajes
        val messages = viewModel.messages
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp) // leave space for text field and button
                .verticalScroll(rememberScrollState()) // enable scrolling
        ) {
            messages.forEach { message ->
                MessageItem(message = message.toString())
            }
        }

        // Campo de entrada de texto y botón de enviar
        val textState = remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text(text = "Type a message...") }
            )
            Button(
                onClick = {
                    viewModel.sendMessage(textState.value)
                    textState.value = ""
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Send")
            }
        }
    }
}

@Composable
fun MessageItem(message: String) {
    Text(text = message)
}

