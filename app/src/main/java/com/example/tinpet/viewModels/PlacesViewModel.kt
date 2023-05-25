package com.example.tinpet.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinpet.screens.Constants
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlacesViewModel: ViewModel() {

    private val _latitude = MutableLiveData<Double>()
    private val _longitude = MutableLiveData<Double>()
    private val _title = MutableLiveData<String>()
    private val _snippet = MutableLiveData<String>()
    val markers = MutableLiveData<List<MarkerOptions>>()

    fun places() {
        val place = hashMapOf(
            "latitude" to _latitude.value,
            "longitude" to _longitude.value,
            "title" to _title.value,
            "snippet" to _snippet.value
        )
        Firebase.firestore.collection(Constants.PLACES)
            .add(place)
    }

    fun loadPlaces(){
        Firebase.firestore.collection(Constants.PLACES)
            .get()
            .addOnSuccessListener { result ->
                val newMarkers = mutableListOf<MarkerOptions>()
                for (document in result) {
                    val latitude = document.getDouble("latitude") ?: 0.0
                    val longitude = document.getDouble("longitude") ?: 0.0
                    val title = document.getString("title") ?: ""
                    val snippet = document.getString("snippet") ?: ""
                    val marker = MarkerOptions()
                        .position(LatLng(latitude, longitude))
                        .title(title)
                        .snippet(snippet)
                    newMarkers.add(marker)
                }
                markers.value = newMarkers
            }
    }

    fun setTitle(newTitle:String){
        _title.value=newTitle
    }
    fun setSnippet(newSnippet:String){
        _snippet.value = newSnippet
    }
    fun setLatitude(newLatitude: Double) {
        _latitude.value = newLatitude
    }
    fun setLongitude(newLongitude: Double) {
        _longitude.value = newLongitude
    }
}