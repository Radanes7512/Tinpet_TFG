package com.example.tinpet.screens

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.tinpet.AppScreens


class LoginViewModel(context:Context) : ViewModel() {

    private val repository:miSQLiteHelper = miSQLiteHelper(context)

    // USER INFO
    private val _number = MutableLiveData<String>()
    val number: LiveData<String> = _number

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

    // VAL FUNCTIONS
    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable: LiveData<Boolean> = _signupEnable

    private val _smsEnable = MutableLiveData<Boolean>()
    val smsEnable: LiveData<Boolean> = _smsEnable

    private val _addpetEnable = MutableLiveData<Boolean>()
    val addpetEnable: LiveData<Boolean> = _addpetEnable

    fun onLoginChanged(number: String, password: String) {
        _number.value = number
        _password.value = password
        _loginEnable.value = isValidNumber(number) && isValidPassword(password)
    }

    fun onSignupChanged(number: String,name: String,password: String, password2:String){
        _number.value = number
        _name.value = name
        _password.value = password
        _password2.value = password2
        _signupEnable.value = isValidNumber(number) && isValidName(name) && isValidPassword(password) && password == password2
    }

    fun onSmsChanged(smscode: String){
        _smscode.value = smscode
        _smsEnable.value = isValidCode(smscode)
    }

    fun onAddpetChanged(petname: String, petage: String){
        _petname.value = petname
        _petage.value = petage
        _addpetEnable.value = isValidPetName(petname) && isValidPetAge(petage)
    }
    fun login(){

    }
    fun register(context:Context){
        number.value?.let { password.value?.let { it1 -> repository.addUser(it, it1) } }
        var resultado = number.value?.let { repository.getUser(it) }
        Toast.makeText(context, "$resultado", Toast.LENGTH_SHORT).show()

    }
    private fun  isValidCode(smscode: String): Boolean = smscode.length == 4
    private fun isValidPassword(password: String): Boolean = password.length > 9

    private fun isValidNumber(number: String): Boolean  = number.length == 9

    //guarripé que siempre es valido, meter condicion base de datos
    private fun isValidName(name: String): Boolean = name.length > 1

    //guarripé que siempre es valido, meter condicion base de datos
    private fun isValidPetName(petname:String): Boolean = petname.length > 1
    private fun isValidPetAge(petage:String): Boolean = petage.length in 1..2
}