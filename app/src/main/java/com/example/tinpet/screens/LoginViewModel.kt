package com.example.tinpet.screens

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tinpet.AppScreens
import com.example.tinpet.graphs.Graph

class LoginViewModel : ViewModel() {

    private val _number = MutableLiveData<String>()
    val number: LiveData<String> = _number

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

    fun onSignupChanged(number: String,password: String, password2:String){
        _number.value = number
        _password.value = password
        _password2.value = password2
        _signupEnable.value = isValidNumber(number) && isValidPassword(password) && password == password2
    }
    private fun isValidPassword(password: String): Boolean = password.length > 9

    private fun isValidNumber(number: String): Boolean  = number.length == 9


}