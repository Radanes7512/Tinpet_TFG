package com.example.tinpet.screens.mainMenu

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.tasks.await
import java.util.*


class ChatViewModel() : ViewModel() {
    //region VARIABLES

    var selectedUserName: String? = null

    val auth = Firebase.auth

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _messagesState = MutableLiveData<List<String>>()
    val messagesState: LiveData<List<String>> = _messagesState

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
                    Constants.SENT_TO to System.currentTimeMillis()
                )
            ).addOnSuccessListener {
                _message.value = ""
            }
        }
    }


    /*  private fun getMessages(chatId: String) {
          val user =auth.currentUser


          if (user != null) {
              Firebase.firestore.collection(Constants.USERS)
                  .whereEqualTo("Email", user.email)
                  .addSnapshotListener { value, e ->
                      if (e != null) {
                          Log.w(Constants.TAG, "Listen failed.", e)
                          return@addSnapshotListener
                      }
                      if (value != null) {

                          for (doc in value) {

                              //Extraemos los datos
                              val currentUser = doc.id
                             // val messages =
                              val users = arrayOf(chatId,currentUser)
                              val conversationdb = hashMapOf(
                                  "users" to users,
                                  //"messages" to
                              )

                          }
                      }
                  }
          }
      }


      private fun createMessages() {
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
      }*/

    fun sendMessage(message: String) {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // Crear o actualizar un documento en la colección "Chats"
            val chatRef = _chatId.value?.let {
                Firebase.firestore.collection(Constants.CHATS).document(
                    it
                )
            }
            if (chatRef != null) {
                chatRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            // El chat ya existe, actualizarlo con el nuevo mensaje
                            val messageData = hashMapOf(
                                Constants.SENT_BY to currentUser.email,
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
                        } else {
                            val chatUserData = chatUserDocument.value?.data
                            if (chatUserData?.get(Constants.EMAIL) != null){
                                val chatData = hashMapOf(
                                Constants.USERS to listOf(currentUser.email,
                                    chatUserData.get(Constants.EMAIL)
                                ),
                                Constants.MESSAGES to listOf(
                                    hashMapOf(
                                        Constants.SENT_BY to currentUser.email,
                                        Constants.MESSAGE to message,
                                        Constants.SENT_AT to Date()
                                    )
                                )
                            )
                                chatRef.set(chatData)
                                    .addOnSuccessListener {
                                        Log.d(Constants.TAG, "Chat creado con éxito")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(Constants.TAG, "Error al crear el chat", e)
                                    }}

                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w(Constants.TAG, "Error al obtener el chat existente", e)
                    }
            }
        }
    }


    public fun callGetMessages(message: String) {
        viewModelScope.launch {
            sendMessage( message)
        }
    }

    fun getMessages(chatId: String, onMessagesFetched: (List<String>) -> Unit) {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Firebase.firestore.collection(Constants.MESSAGES)
                .whereEqualTo(Constants.SENT_BY, currentUser.uid)
                .whereEqualTo(Constants.SENT_TO, chatId)
                .orderBy(Constants.SENT_TO)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w(Constants.TAG, "Error al obtener los mensajes", error)
                        return@addSnapshotListener
                    }

                    if (value != null) {
                        val messages = mutableListOf<String>()
                        for (doc in value) {
                            val message = doc.getString(Constants.MESSAGE)
                            if (message != null) {
                                messages.add(message)
                            }
                        }

                        // Llamar la función de devolución de llamada con los mensajes recuperados
                        onMessagesFetched(messages)
                    }
                }
        }
    }

    fun fetchMessages(chatId: String) {
        getMessages(chatId) { messages ->
            viewModelScope.launch {
                _messagesState.value = messages
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


    fun getUsers() {
        Firebase.firestore.collection("users")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val list = emptyList<Map<String, String>>().toMutableList()
                //Value es el estado de la base de datos en el momento que lo recibimos (listener)
                if (value != null) {
                    //Leemos cada uno de los documentos dentro de la coleccion "Mensajes" de la base de datos
                    for (doc in value) {

                        //Extraemos los datos
                        val data = doc.data
                        val userMap = mapOf<String, String>(
                            "id" to doc.id,
                            "name" to data.get("Username").toString()
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

                                            val users = chatData[Constants.USERS] as Array<String>
                                            val contieneTodos = users.all { valor -> listOf(it,userData.get(Constants.EMAIL)).contains(valor) }
                                            if (contieneTodos){
                                                _chatId.value = doc.id
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
                Log.w(Constants.TAG, "Error al obtener el usuario existente", e)
            }



    }
    //endregion
}

