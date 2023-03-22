package com.example.tinpet.screens.mainMenu

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {
    //region VARIABLES
    val messages = mutableStateOf(listOf<String>())
    var selectedUserName: String? = null
    //endregion

    //region FUNCIONES
    fun sendMessage(message: String) {
        val currentMessages = messages.value.toMutableList()
        currentMessages.add(message)
        messages.value = currentMessages
    }
    fun onUserSelected(name: String) {
        selectedUserName = name
    }
    //endregion
}

