package com.example.tinpet.viewModels

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinpet.MainActivity
import com.example.tinpet.R
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@RequiresApi(Build.VERSION_CODES.O)
class RequestsViewModel(context: Context): ViewModel() {

    private val _FriendList = MutableLiveData<List<Map<String, String>>>()
    val FriendList: LiveData<List<Map<String, String>>> = _FriendList

    val _sendedRequest = MutableLiveData<String?>()
    val sendedRequest: LiveData<String?> = _sendedRequest

    val auth = Firebase.auth
    val currentUser = auth.currentUser
    init {
        if (currentUser != null) {
            Firebase.firestore.collection("friendRequests")
                .whereEqualTo(Constants.SENT_TO, currentUser.email)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        // maneja el error aquí
                        return@addSnapshotListener
                    }
                    if (snapshot != null && !snapshot.isEmpty) {
                        // hay nuevas solicitudes de amistad
                        // envía una notificación al usuario aquí
                        //region NOTIFICACION

                        //endregion
                    }
                }
        }
    }


    fun CheckFriendRequests() {
        if (currentUser != null) {

            Firebase.firestore.collection(Constants.PENDING_REQUESTS)
                .whereEqualTo(Constants.SENT_TO, currentUser.email)
                .whereEqualTo(Constants.STATE, Constants.PENDING)
                .addSnapshotListener { value, error ->

                    if (value != null) {
                        var userList = mutableListOf<Map<String, String>>()
                        for (doc in value) {
                            var userData = doc.data as MutableMap<String, String>
                            userData.put("id", doc.id)
                            userList.add(userData)

                        }
                        _FriendList.value = userList

                    }


                }
        }
    }


    fun onFriendRequestUpdate(friendRequest: Map<String,String>, state : String) {

        val currentUser = auth.currentUser
        if (currentUser != null) {

            friendRequest["id"]?.let {
               val frRef =  Firebase.firestore.collection(Constants.PENDING_REQUESTS).document(it)
                frRef?.get()?.addOnSuccessListener { document ->
                    if (document.exists()) {
                    frRef.update(Constants.STATE, state)
                    }
                }

            }
        }

    }

}
