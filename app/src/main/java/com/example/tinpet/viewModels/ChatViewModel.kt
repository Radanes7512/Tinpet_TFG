package com.example.tinpet.viewModels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class ChatViewModel() : ViewModel() {
    //region VARIABLES

    var selectedUserName: String? = null

    val auth = Firebase.auth

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _messagesState = MutableLiveData<List<Map<String, String>>>()
    val messagesState: LiveData<List<Map<String, String>>> = _messagesState

    private val _loggedUserId = MutableLiveData<String>()
    val loggedUserId: LiveData<String> = _loggedUserId

    private val _chatId = MutableLiveData<String>()
    val chatId: LiveData<String> = _chatId

    private val _chatUserDocument = MutableLiveData<DocumentSnapshot>()
    val chatUserDocument: LiveData<DocumentSnapshot> = _chatUserDocument

    private var _messages = MutableLiveData(emptyList<String>().toMutableList())
    val messages: LiveData<MutableList<String>> = _messages

    private val _usernames = MutableLiveData(emptyList<Map<String, String>>().toMutableList())
    val usernames: LiveData<MutableList<Map<String, String>>> = _usernames


    //endregion

    //region FUNCIONES

    init {

        getFriendList()

    }

    //update del mensaje que enviamos
    fun updateMessage(message: String) {
        _message.value = message
    }

    fun sendMessage(message: String) {
        val currentUser = auth.currentUser
        //Variable temporal
        val currentUserEmail = auth.currentUser?.email


        if (currentUser != null) {
            // Crear o actualizar un documento en la colección "Chats"
            val chatRef = chatId.value?.let {
                Firebase.firestore.collection(Constants.CHATS).document(
                    it
                )
            }
           if(chatRef == null) {
               val userData = _chatUserDocument.value?.data
               val chat = hashMapOf(
                   Constants.USERS to userData?.let {
                       listOf(
                           currentUser.email,
                           it[Constants.EMAIL]
                       )
                   },
                 Constants.MESSAGES to kotlin.collections.listOf(
                      hashMapOf(
                           Constants.SENT_BY to currentUser.email.toString(),
                           Constants.MESSAGE to message,
                           Constants.SENT_AT to java.util.Date()
                       )
                   )
               )
               Firebase.firestore.collection(Constants.CHATS)
                   .add(chat)
                   .addOnSuccessListener { documentReference ->
                       Log.w(ContentValues.TAG, "Success adding document" )
                   }
                   .addOnFailureListener { e ->
                       Log.w(ContentValues.TAG, "Error adding document", e)
                   }
            }
            chatRef?.get()?.addOnSuccessListener { document ->
                if (document.exists()) {
                    // El chat ya existe, actualizarlo con el nuevo mensaje
                    val messageData = hashMapOf(
                        Constants.SENT_BY to currentUser.email.toString(),
                        Constants.MESSAGE to message,
                        Constants.SENT_AT to Date()
                    )
                    chatRef.update(Constants.MESSAGES, FieldValue.arrayUnion(messageData))
                        .addOnSuccessListener {
                            Log.d(Constants.TAG, "Mensaje enviado con éxito")
                        }
                        .addOnFailureListener { e ->
                            Log.w(Constants.TAG, "Error al enviar el mensaje", e)
                        }
                }
            }?.addOnFailureListener { e ->
                Log.w(Constants.TAG, "Error al obtener el chat existente", e)
            }
        }
    }

    fun getMessages(chatId: String) {
        val currentUser = auth.currentUser
        val userData = chatUserDocument.value?.data
        if (currentUser != null && userData?.get(Constants.EMAIL) != null) {
            currentUser.email?.let {
                Firebase.firestore.collection(Constants.CHATS)
                    .whereArrayContains(Constants.USERS, it)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(Constants.TAG, "Error al obtener los mensajes", error)
                            return@addSnapshotListener
                        }
                        if (value != null) {
                            for (doc in value) {
                                var chatData = doc.data

                                val users = chatData[Constants.USERS] as ArrayList<*>
                                val contieneTodos = users.all { valor -> listOf(it,userData.get(Constants.EMAIL)).contains(valor) }
                                if (contieneTodos){
                                    _chatId.value = doc.id
                                    val messages = chatData[Constants.MESSAGES] as ArrayList<*>
                                    val reversedMessages = messages.reversed()
                                    viewModelScope.launch {
                                        _messagesState.value = reversedMessages as List<Map<String, String>>

                                    }
                                }
                                println("El array contiene todos los valores especificados: $contieneTodos")
                            }

                        }
                    }
            }
        }
    }

    private fun updateMessages(list: MutableList<Map<String, Any>>) {

        val messageList = emptyList<String>().toMutableList()
        for (message in list) {
            messageList.add(message.get("message").toString())
        }
        _messages.value = messageList.asReversed()
    }

    fun  getFriendList(){
        val currentUser = auth.currentUser
        if (currentUser != null) {
            var email = auth.currentUser?.email
            email?.let {
                Firebase.firestore.collection(Constants.PENDING_REQUESTS)
                    .whereArrayContains(Constants.EMAILS, it)
                    .whereEqualTo(Constants.STATE, Constants.ACCEPTED)
                    .addSnapshotListener { value, error ->

                        if (value != null) {
                            var userList = mutableListOf<String>()
                            for (doc in value) {
                                val requestData = doc.data as MutableMap<String, String>
                                val sentTo  = requestData[Constants.SENT_TO]
                                val sentBy  = requestData[Constants.SENT_BY]
                                if (!sentTo.equals(auth.currentUser!!.email)){
                                    if (sentTo != null && !userList.contains(sentTo)) {
                                        userList.add(sentTo)
                                    }
                                }else {
                                    if (sentBy != null &&  !userList.contains(sentBy)) {
                                        userList.add(sentBy)
                                    }
                                }
                            }
                            if (!userList.isEmpty()) {
                                getUsers(userList)
                            }
                        }


                    }
            }
        }
    }

    fun getUsers(friendList: List<String>) {
        Firebase.firestore.collection("users")
            .whereIn(Constants.EMAIL, friendList)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val list = emptyList<Map<String, String>>().toMutableList()
                if (value != null) {
                    //Leemos cada uno de los documentos dentro de la coleccion "Mensajes" de la base de datos
                    for (doc in value) {

                        //Extraemos los datos
                        val data = doc.data
                        val userMap = mapOf<String, String>(
                            "id" to doc.id,
                            "name" to data["Username"].toString(),
                            "pic" to data["photo"].toString()
                        )
                        //Añadimos info de si los mensajes son nuestros o no
                        list.add(userMap)

                    }
                }
                getUsernames(list)
            }
    }

    private fun getUsernames(list: MutableList<Map<String, String>>) {

        val usernameList = emptyList<Map<String, String>>().toMutableList()
        for (user in list) {
            usernameList.add(user)
        }
        _usernames.value = usernameList
    }



    fun getChat(chatUserId: String) {
        val userRef = Firebase.firestore.collection(Constants.USERS).document(chatUserId)
        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val currentUser = auth.currentUser
                    _chatUserDocument.value = document
                    val userData = document.data
                    if (currentUser != null && userData?.get(Constants.EMAIL) != null) {
                        currentUser.email?.let {
                            Firebase.firestore.collection(Constants.CHATS)
                                .whereArrayContains(Constants.USERS, it)
                                .addSnapshotListener { value, error ->
                                    if (error != null) {
                                        Log.w(Constants.TAG, "Error al obtener los mensajes", error)
                                        return@addSnapshotListener
                                    }
                                    if (value != null) {
                                        for (doc in value) {
                                            var chatData = doc.data

                                            val users = chatData[Constants.USERS] as ArrayList<*>
                                            val contieneTodos = users.all { valor -> listOf(it,userData.get(Constants.EMAIL)).contains(valor) }
                                            if (contieneTodos){
                                                _chatId.value = doc.id
                                                val messages = chatData[Constants.MESSAGES] as ArrayList<*>
                                                _messagesState.value = messages as List<Map<String, String>>

                                            }
                                            println("El array contiene todos los valores especificados: $contieneTodos")
                                        }

                                    }
                                }
                        }
                    }
                }


            }
            .addOnFailureListener { e ->
                Log.w(Constants.TAG, "Error al obtener el usuario existente",)
            }
    }


    fun isCurrentUserMessage(message : Map<String, String> ): Boolean {

        return message[Constants.SENT_BY] == auth.currentUser?.email
    }
    //endregion
}

