package com.example.tinpet.screens.mainMenu

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {
    val messages = mutableStateOf(listOf<String>())

    fun sendMessage(message: String) {
        val currentMessages = messages.value.toMutableList()
        currentMessages.add(message)
        messages.value = currentMessages
    }
}

