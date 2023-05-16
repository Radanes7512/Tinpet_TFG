package com.example.tinpet.screens.mainMenu.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RequestsViewModel(): ViewModel() {

    private val _FriendList = MutableLiveData<List<Map<String, String>>>()
    val FriendList: LiveData<List<Map<String, String>>> = _FriendList

    val auth = Firebase.auth

    fun CheckFriendRequests()
    {
        val currentUser = auth.currentUser
        if (currentUser != null) {

            /* Firebase.firestore.collection(Constants.USERS).whereEqualTo(Constants.EMAIL,currentUser.email)
                .addSnapshotListener { value, error ->

                }*/
            Firebase.firestore.collection(Constants.PENDING_REQUESTS)
                .whereEqualTo(Constants.SENT_TO, currentUser.email)
                .addSnapshotListener { value, error ->

                    if (value != null) {
                        var userList = mutableListOf<Map<String,String>>()
                        for (doc in value) {
                            var userData = doc.data as Map<String,String>
                            userList.add(userData)

                        }
                        _FriendList.value = userList

                    }


                }
        }
    }
}