package com.example.tinpet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun PlacesScreen(){
    val properties by remember {mutableStateOf(MapProperties(mapType = MapType.SATELLITE))}
    val uiSettings by remember {mutableStateOf(MapUiSettings(zoomControlsEnabled = false))}

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings
        ){
        Marker(position = LatLng(40.421290016336684, -3.543930244461727), title = "Parque la Colina", snippet = "Zona muy transcurrida")
        Marker(position = LatLng(40.46036862008319, -3.450723624671722), title = "Parque de la Mancha Amarilla", snippet = "Zona transcurrida")
        Marker(position = LatLng(40.42373493132333, -3.53992243227222), title = "Parque canino", snippet = "Zona poco transcurrida")
    }
}


@Composable
@Preview
fun PlacesScreenPreview() {
    PlacesScreen()
}