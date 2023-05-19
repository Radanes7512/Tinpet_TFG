package com.example.tinpet.screens

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.protobuf.Empty


class LoginViewModel(context: Context, navController: NavController) : ViewModel() {
    //Context parameter
    private val applicationContext = context.applicationContext

    private val auth = Firebase.auth

    private val rtdb = Firebase.database.reference

    private val Firestore = Firebase.firestore



    // USER INFO

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    var username = ""

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _password2 = MutableLiveData<String>()
    val password2: LiveData<String> = _password2

    // PET INFO
    private val _petname = MutableLiveData<String>()
    val petname: LiveData<String> = _petname

    private val _petage = MutableLiveData<String>()
    val petage: LiveData<String> = _petage

    private val _petImageUri = MutableLiveData<Uri>()
    val petImageUri: LiveData<Uri> = _petImageUri

    private val _petcategory = MutableLiveData<String>()
    val petcategory: LiveData<String> = _petcategory

    // VAL FUNCTIONS
    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable: LiveData<Boolean> = _signupEnable

    private val _emailVerified = MutableLiveData<Boolean>()
    val emailVerified: LiveData<Boolean> = _emailVerified

    var regState : Boolean = false

    private val _addpetEnable = MutableLiveData<Boolean>()
    val addpetEnable: LiveData<Boolean> = _addpetEnable

    var uiState = mutableStateOf<UiState>(UiState.SignedOut)

    fun onLoginChanged(email: String, password: String) {
        _email.value= email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    fun onSignupChanged(email: String,name: String,password: String, password2:String){
        _name.value = name
        _email.value= email
        _password.value = password
        _password2.value = password2
        _signupEnable.value = isValidEmail(email) && isValidName(name) && isValidPassword(password) && password == password2
        }


    fun onAddpetChanged(petname: String, petage: String, petcategory: String, petimage: Uri){
        _petname.value = petname
        _petage.value = petage
        _petcategory.value = petcategory
        _petImageUri.value = petimage
        _addpetEnable.value = isValidPetCategory(petcategory) && isValidPetName(petname) && isValidPetAge(petage) && isValidPetImage(petimage)
    }

    fun login(context: Context){
        email.value?.let {
            password.value?.let { it1 ->
                auth.signInWithEmailAndPassword(it, it1)
                    .addOnCompleteListener(context as Activity) { task ->
                        if (task.isSuccessful) {
                            // Si es correcto el login sale un mensaje al usuario y se envia el email
                            Log.d(TAG, "signInWithEmail:success")
                            checkIfEmailVerified()
                        } else {
                            // Si el login falla sale un mensaje al usuario
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
                            val userdb = hashMapOf(
                                "Username" to name.value,
                                "Email" to email.value,
                                "Petname" to petname.value,
                                "Petage" to petage.value,
                                "Friends" to ArrayList<String>(),
                                "photo" to petImageUri.value as String
                            )
                            Firestore.collection("users")
                                .add(userdb)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")

                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success")
                                    val user = auth.currentUser
                                    sendVerificationEmail()
                                    name.value?.let { it2 -> writeNewUser(documentReference.id, it2, email.value!!) }
                                    readUser()
                                    Toast.makeText(context, "Se ha creado su cuenta correctamente",
                                         Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                        } else {

                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(context, "Ha ocurrido un error, en el registro",
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

    fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)
        rtdb.child("users").child(userId).setValue(user)

    }

    fun readUser() {

        rtdb.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val value = dataSnapshot.value as Map<String, Any>?
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {

                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }





    private fun isValidPassword(password: String): Boolean = password.length >= 6
    private fun isValidEmail(email: String): Boolean  = email.contains("@gmail.com") || email.contains("@hotmail.com")
    private fun isValidName(name: String): Boolean = name.length > 1
    private fun isValidPetName(petname:String): Boolean = petname.length > 1
    private fun isValidPetCategory(petcategory:String): Boolean = petcategory.length > 1
    private fun isValidPetAge(petage:String): Boolean = petage.length in 1..2
    private fun isValidPetImage(petimage: Uri): Boolean = petimage.isRelative
/*{
        val isImageExists = try {
            context.checkUriPermission(petimage, null, null, Intent.FLAG_GRANT_READ_URI_PERMISSION) == PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) {
            false
        }
        return isImageExists && petimage.isAbsolute
    }*/

}