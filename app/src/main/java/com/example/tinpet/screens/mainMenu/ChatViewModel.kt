package com.example.tinpet.screens.mainMenu

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatViewModel() : ViewModel() {
    //region VARIABLES

    var selectedUserName: String? = null

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var _messages = MutableLiveData(emptyList<String>().toMutableList())
    val messages: LiveData<MutableList<String>> = _messages

    private val _usernames = MutableLiveData(emptyList<String>().toMutableList())
    val usernames: LiveData<MutableList<String>> = _usernames

    var test:String = ""

    //endregion

    //region FUNCIONES

    init {
        test =test
        getMessages()
        getUsers()
    }

    //update del mensaje que enviamos
    fun updateMessage(message: String) {
        _message.value = message
    }


    fun addMessage() {
        val message: String = _message.value ?: throw IllegalArgumentException("message empty")
        if (message.isNotEmpty()) {
            Firebase.firestore.collection(Constants.MESSAGES).document().set(
                hashMapOf(
                    Constants.MESSAGE to message,
                    Constants.SENT_BY to Firebase.auth.currentUser?.uid,
                    Constants.SENT_ON to System.currentTimeMillis()
                )
            ).addOnSuccessListener {
                _message.value = ""
            }
        }
    }


    private fun getMessages() {
        Firebase.firestore.collection(Constants.MESSAGES)
            .orderBy(Constants.SENT_ON)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val list = emptyList<Map<String, Any>>().toMutableList()
                //Value es el estado de la base de datos en el momento que lo recibimos (listener)
                if (value != null) {
                    //Leemos cada uno de los documentos dentro de la coleccion "Mensajes" de la base de datos
                    for (doc in value) {
                        //Extraemos los datos
                        val data = doc.data
                        //Añadimos info de si los mensajes son nuestros o no
                        data[Constants.IS_CURRENT_USER] =
                            Firebase.auth.currentUser?.uid.toString() == data[Constants.SENT_BY].toString()
                        list.add(data)
                    }
                }

                updateMessages(list)
            }
    }

    private fun updateMessages(list: MutableList<Map<String, Any>>) {

        val messageList = emptyList<String>().toMutableList()
        for (message in list){
            messageList.add(message.get("message").toString())
        }
        _messages.value =messageList.asReversed()
    }

    fun getUsers() {
        Firebase.firestore.collection("users")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val list = emptyList<Map<String, Any>>().toMutableList()
                //Value es el estado de la base de datos en el momento que lo recibimos (listener)
                if (value != null) {
                    //Leemos cada uno de los documentos dentro de la coleccion "Mensajes" de la base de datos
                    for (doc in value) {
                        //Extraemos los datos
                        val data = doc.data
                        val test = data.get("userinfo")
                        //Añadimos info de si los mensajes son nuestros o no
                        if (test != null)
                            list.add(test as Map<String, Any>)
                    }
                }
                getUsernames(list)
            }
    }

    private fun getUsernames(list: MutableList<Map<String,Any>>) {

        val usernameList = emptyList<String>().toMutableList()
        for (user in list){
            usernameList.add(user.get("value").toString())
        }
        _usernames.value = usernameList
    }

    fun assingUsers(name:String){
    test= name
    }
    //endregion
}

