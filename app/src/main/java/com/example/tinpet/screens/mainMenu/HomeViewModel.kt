package com.example.tinpet.screens.mainMenu

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    val auth = Firebase.auth

    private val _UserPets = MutableLiveData<List<Map<String, String>>>()
    val UserPets: LiveData<List<Map<String, String>>> = _UserPets

    private val _loggedUserName = MutableLiveData<String>()
    val loggedUserName: LiveData<String> = _loggedUserName


    fun getUserPets(){

        val currentUser = auth.currentUser
        if (currentUser != null) {

            Firebase.firestore.collection(Constants.USERS)
                .whereNotEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w(Constants.TAG, "Error al obtener los datos de las mascotas", error)
                        return@addSnapshotListener
                    }
                    if (value != null) {
                        var userList = mutableListOf<Map<String,String>>()
                        for (doc in value) {
                            var userData = doc.data as Map<String,String>
                            userList.add(userData)

                        }
                        _UserPets.value = userList
                        //showendbox false

                    }



                }
        }
    }
    fun SendFriendRequests(Email: String?) {

        val currentUser = auth.currentUser

        if (currentUser != null) {

            Firebase.firestore.collection(Constants.USERS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w(Constants.TAG, "Error al obtener los datos de las mascotas", error)
                        return@addSnapshotListener
                    }
                }
            val PendingRequest = hashMapOf(
                Constants.PET_NAME to _loggedUserName.value,
                Constants.SENT_TO to Email,
                Constants.SENT_BY to currentUser.email,
            )
            Firebase.firestore.collection(Constants.PENDING_REQUESTS)
            .add(PendingRequest)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot written with ID: ${documentReference.id}"
                )
            }
        }

    }

    public fun getLoggedUser(){


        val currentUser = auth.currentUser

        if (currentUser != null) {

            Firebase.firestore.collection(Constants.USERS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        for (doc in value) {
                            var userData = doc.data

                          _loggedUserName.value= userData[Constants.PET_NAME] as String?

                        }
                    }
                }
        }
    }



}