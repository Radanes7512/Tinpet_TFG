package com.example.tinpet.screens.mainMenu

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinpet.screens.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    val auth = Firebase.auth

    private val _UserPets = MutableLiveData<List<MutableMap<String, String>>>()
    val UserPets: LiveData<List<MutableMap<String, String>>> = _UserPets

    private val _loggedUserName = MutableLiveData<String>()
    val loggedUserName: LiveData<String> = _loggedUserName

    private val _loggedUserImage = MutableLiveData<Uri>()
    val loggedUserImage: LiveData<Uri> = _loggedUserImage

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
                        var userList = mutableListOf<MutableMap<String,String>>()
                        for (doc in value) {
                            var userData = doc.data as MutableMap<String,String>
                            userList.add(userData)
                        }
                        _UserPets.value = userList

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
    fun getLoggedUser(){
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