package com.example.tinpet.screens.mainMenu

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class HomeViewModel() : ViewModel() {

    val auth = Firebase.auth


    private fun SendFriendRequests(){

    }
    private fun CheckFriendRequests()
    {
        val currentUser = auth.currentUser
        if (currentUser != null) {

            /* Firebase.firestore.collection(Constants.USERS).whereEqualTo(Constants.EMAIL,currentUser.email)
                .addSnapshotListener { value, error ->

                }*/
            Firebase.firestore.collection(Constants.PENDING_REQUESTS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->


                }
        }
    }


           /* .addOnSuccessListener { document ->
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
                                }
                                */
            */

}