package com.example.tinpet.screens

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class LoginViewModel(context: Context) : ViewModel() {

    //Context parameter
    private val applicationContext = context.applicationContext

    private val auth = Firebase.auth


    // USER INFO

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _password2 = MutableLiveData<String>()
    val password2: LiveData<String> = _password2

    // SMS
    private val _smscode = MutableLiveData<String>()
    val smscode: LiveData<String> = _smscode

    // PET INFO
    private val _petname = MutableLiveData<String>()
    val petname: LiveData<String> = _petname

    private val _petage = MutableLiveData<String>()
    val petage: LiveData<String> = _petage

    private val _petImageUri = MutableLiveData<Uri>()
    val petImageUri: LiveData<Uri> = _petImageUri

    // VAL FUNCTIONS
    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable: LiveData<Boolean> = _signupEnable

    private val _smsEnable = MutableLiveData<Boolean>()
    val smsEnable: LiveData<Boolean> = _smsEnable

    private val _addpetEnable = MutableLiveData<Boolean>()
    val addpetEnable: LiveData<Boolean> = _addpetEnable

    var uiState = mutableStateOf<UiState>(UiState.SignedOut)

    fun onLoginChanged(number: String, password: String) {
        _email.value= number
        _password.value = password
        _loginEnable.value = isValidNumber(number) && isValidPassword(password)
    }

    fun onSignupChanged(email: String,name: String,password: String, password2:String){

        _name.value = name
        _email.value= email
        _password.value = password
        _password2.value = password2
        _signupEnable.value = isValidNumber(email) && isValidName(name) && isValidPassword(password) && password == password2
    }


    fun onAddpetChanged(petname: String, petage: String){
        _petname.value = petname
        _petage.value = petage
        _addpetEnable.value = isValidPetName(petname) && isValidPetAge(petage)
    }
    fun login(context: Context){
        Log.d(TAG, "email: "+email.value)
        Log.d(TAG, "email: "+password.value)
        email.value?.let {
            password.value?.let { it1 ->
                auth.signInWithEmailAndPassword(it, it1)
                    .addOnCompleteListener(context as Activity) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            checkIfEmailVerified()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }

    }
    fun register(context:Context){
        email.value?.let {
            password.value?.let { it1 ->
                auth.createUserWithEmailAndPassword(it, it1)
                    .addOnCompleteListener(context as Activity) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            sendVerificationEmail()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
    private fun sendVerificationEmail() {
        val user = auth.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
    }
    private fun checkIfEmailVerified() {
        val user = auth.currentUser
        if (user!!.isEmailVerified()) {
            uiState.value = UiState.SignIn

        } else {

            FirebaseAuth.getInstance().signOut()

        }
    }

    private fun isValidPassword(password: String): Boolean = true

    private fun isValidNumber(number: String): Boolean  = true
    private fun isValidName(name: String): Boolean = name.length > 1
    private fun isValidPetName(petname:String): Boolean = petname.length > 1
    private fun isValidPetAge(petage:String): Boolean = petage.length in 1..2



}