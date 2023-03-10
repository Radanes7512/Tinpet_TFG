package com.example.tinpet.screens.mainMenu


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun PlacesScreen(){
    val properties by remember {mutableStateOf(MapProperties(mapType = MapType.SATELLITE))}
    val uiSettings by remember {mutableStateOf(MapUiSettings(zoomControlsEnabled = false))}
    //val maxZoom by remeber { mutableStateOf(Map)}
    val spain = LatLng(39.53721928672391, -3.416821204546524)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(spain, 5.5f)

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp, 16.dp, 16.dp,100.dp)
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            Marker(
                position = LatLng(40.421290016336684, -3.543930244461727),
                title = "Parque la Colina",
                snippet = "Zona muy transcurrida"
            )
            Marker(
                position = LatLng(40.46036862008319, -3.450723624671722),
                title = "Parque de la Mancha Amarilla",
                snippet = "Zona transcurrida"
            )
            Marker(
                position = LatLng(40.42373493132333, -3.53992243227222),
                title = "Parque canino",
                snippet = "Zona poco transcurrida"
            )
        }
    }
}


@Composable
@Preview
fun PlacesScreenPreview() {
    PlacesScreen()
}