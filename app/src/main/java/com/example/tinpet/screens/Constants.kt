package com.example.tinpet.screens

object Constants {

        const val TAG = "flash-chat"
        const val MESSAGES = "messages"
        const val MESSAGE = "message"
        const val SENT_BY = "sent_by"
        const val SENT_TO = "sent_to"
        const val SENT_AT = "sent_at"
        const val CHATS = "chat"
        const val EMAIL = "Email"
        const val IS_CURRENT_USER = "is_current_user"
        const val CONVERSATIONS = "conversations"
        const val USERS = "users"
        const val USER1_ID = "user1_id"
        const val USER2_ID = "user2_id"
        const val LAST_MESSAGE = "last_message"
        const val LAST_MESSAGE_TIME = "last_message_time"

}

/*fun sendMessage2(message: String) {
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
   }*/