package com.example.tinpet.viewModels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinpet.Constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileViewModel : ViewModel() {

    private val _profileName = MutableLiveData<String?>()
    val profileName: LiveData<String?> = _profileName

    private val _profileAge = MutableLiveData<String?>()
    val profileAge: LiveData<String?> = _profileAge

    private val _profileCategory = MutableLiveData<String?>()
    val profileCategory: LiveData<String?> = _profileCategory

    private val _profilePhoto = MutableLiveData<Uri?>()
    val profilePhoto: LiveData<Uri?> = _profilePhoto


    val auth = Firebase.auth
    val currentUser = auth.currentUser


    fun getName() {
        if (currentUser != null) {
            var name = ""
            Firebase.firestore.collection(Constants.USERS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        for (doc in value) {
                            val requestData = doc.data
                            name = requestData[Constants.PET_NAME].toString()
                            break
                        }

                        _profileName.value = name
                    }
                }
        }
    }

    fun getCategory() {
        if (currentUser != null) {
            var name = ""
            Firebase.firestore.collection(Constants.USERS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        for (doc in value) {
                            val requestData = doc.data
                            name = requestData[Constants.PET_CATEGORY].toString()
                            break
                        }

                        _profileCategory.value = name
                    }
                }
        }


    }

    fun getAge() {
        if (currentUser != null) {
            var name = ""
            Firebase.firestore.collection(Constants.USERS)
                .whereEqualTo(Constants.EMAIL, currentUser.email)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        for (doc in value) {
                            val requestData = doc.data
                            name = requestData[Constants.PET_AGE].toString()
                            break
                        }

                        _profileAge.value = name
                    }
                }
        }
    }


}