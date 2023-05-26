package com.example.tinpet.viewModels

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinpet.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {

    val auth = Firebase.auth

    private val _UserPets = MutableLiveData<List<MutableMap<String, String>>>()
    val UserPets: LiveData<List<MutableMap<String, String>>> = _UserPets

    private val _loggedUserName = MutableLiveData<String>()
    val loggedUserName: LiveData<String> = _loggedUserName

    private val _loggedUserImage = MutableLiveData<Uri>()
    val loggedUserImage: LiveData<Uri> = _loggedUserImage

    fun getUserPets(emails: List<String>) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Firebase.firestore.collection(Constants.USERS).whereNotIn(Constants.EMAIL, emails)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w(Constants.TAG, "Error al obtener los datos de las mascotas", error)
                        return@addSnapshotListener
                    }
                    if (value != null) {
                        var userList = mutableListOf<MutableMap<String, String>>()
                        for (doc in value) {
                            var userData = doc.data as MutableMap<String, String>
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
                Constants.STATE to Constants.PENDING,
                Constants.EMAILS to listOf<String>(Email.toString(), currentUser.email.toString())
            )
            Firebase.firestore.collection(Constants.PENDING_REQUESTS).add(PendingRequest)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        ContentValues.TAG,
                        "DocumentSnapshot written with ID: ${documentReference.id}"
                    )
                }
        }

    }

    fun getLoggedUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Firebase.firestore.collection(Constants.USERS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        for (doc in value) {
                            var userData = doc.data
                            _loggedUserName.value = userData[Constants.PET_NAME] as String?
                        }
                    }
                }
        }
    }

    fun getNonFriends() {
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
                            userList.add(email)
                            for (doc in value) {
                                val requestData = doc.data as MutableMap<String, String>
                                val sentTo = requestData[Constants.SENT_TO]
                                val sentBy = requestData[Constants.SENT_BY]
                                if (!sentTo.equals(auth.currentUser!!.email)) {
                                    if (sentTo != null && !userList.contains(sentTo)) {
                                        userList.add(sentTo)
                                    }
                                } else {
                                    if (sentBy != null && !userList.contains(sentBy)) {
                                        userList.add(sentBy)
                                    }
                                }
                            }
                            if (!userList.isEmpty()) {
                                getUserPets(userList)
                            }
                        }
                    }
            }
        }
    }
}