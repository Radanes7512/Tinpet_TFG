package com.example.tinpet.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.tinpet.AppScreens
import com.example.tinpet.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import java.util.concurrent.TimeUnit


class LoginViewModel(context: Context) : ViewModel() {

    private val applicationContext = context.applicationContext

    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private val _verifyNumber = MutableLiveData<String>()
    val verifyNumber: LiveData<String> = _verifyNumber

    private var storedVerificationId: String? = ""

    val TAG = "login"


    private val auth = Firebase.auth

    //private val repository:miSQLiteHelper = miSQLiteHelper(context)

    private val _number = MutableLiveData<String>()
    val number: LiveData<String> = _number


    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _password2 = MutableLiveData<String>()
    val password2: LiveData<String> = _password2

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable: LiveData<Boolean> = _signupEnable

    fun onLoginChanged(number: String, password: String) {
        _number.value = number
        _password.value = password
        _loginEnable.value = isValidNumber(number) && isValidPassword(password)
    }
    fun onVerifyNumberChanged(verifyNumber:String){
        _verifyNumber.value = verifyNumber
    }

    fun onSignupChanged(number: String,name: String,password: String, password2:String){
        _number.value = number
        _name.value = name
        _password.value = password
        _password2.value = password2
        _signupEnable.value = isValidNumber(number) && isValidName(name) && isValidPassword(password) && password == password2
    }
    fun login(){


    }
    fun register(context:Context){
        val options = number.value?.let {
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(it)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context as Activity)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                        Log.d(TAG, "onVerificationCompleted:$credential")
                        signInWithPhoneAuthCredential(credential)
                    }

                    override fun onVerificationFailed(e: FirebaseException) {

                        Log.w(TAG, "onVerificationFailed", e)

                        if (e is FirebaseAuthInvalidCredentialsException) {

                        } else if (e is FirebaseTooManyRequestsException) {

                        }
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                        super.onCodeSent(verificationId,token)

                        Log.d(TAG, "onCodeSent:$verificationId")


                        storedVerificationId = verificationId
                        resendToken = token
                    }
                }
                )
                .build()
        }
        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

    }
    fun autenticate(){
        Log.d(TAG, "Credenciales 1: "+ this.verifyNumber.value)
        val credential: PhoneAuthCredential? =
            storedVerificationId?.let { verifyNumber.value?.let { it1 ->
                PhoneAuthProvider.getCredential(it,
                    it1
                )
            } }
        Log.d(TAG, "Credenciales 2 : "+ credential)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(applicationContext as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
    private fun isValidPassword(password: String): Boolean = true

    private fun isValidNumber(number: String): Boolean  = true
    private fun isValidNumber(number:String,phoneOutput:String,password:String, passwordOutput:String): Boolean  = number.length == 9 && number==phoneOutput
    //guarripé que siempre es valido, meter condicion base de datos
    private fun isValidName(name: String): Boolean = name.length > 1




}