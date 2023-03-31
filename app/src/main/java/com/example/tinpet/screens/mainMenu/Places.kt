package com.example.tinpet.screens.mainMenu


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlacesScreen() {
    var search by remember { mutableStateOf("") }
    val markers = listOf(
        Marker(
            position = LatLng(40.421290016336684, -3.543930244461727),
            title = "Parque la Colina",
            snippet = "Zona muy transcurrida"
        ),
        Marker(
            position = LatLng(40.46036862008319, -3.450723624671722),
            title = "Parque de la Mancha Amarilla",
            snippet = "Zona transcurrida"
        ),
        Marker(
            position = LatLng(40.42373493132333, -3.53992243227222),
            title = "Parque canino",
            snippet = "Zona poco transcurrida"
        )
    )
    /*val filteredMarkers = markers.filter { marker ->
        marker.title.contains(search, ignoreCase = true)
    }*/
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.SATELLITE)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }
    val spain = LatLng(39.53721928672391, -3.416821204546524)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(spain, 5.5f)
    }

    Scaffold(
        topBar = {
            TopBar(search, onSearchChange = { newSearch ->
                search = newSearch
            })
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 100.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = properties,
                    uiSettings = uiSettings
                ) {
                   /* markers.forEach {
                        Marker(
                            position = it.position,
                            title = it.title,
                            snippet = it.snippet
                        )
                    }*/
                }
                /*if (filteredMarkers.isNotEmpty()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Resultados:",
                            fontFamily = abrilFatface,
                            modifier = Modifier.padding(16.dp)
                        )
                        LazyColumn {
                            items(filteredMarkers) { marker ->
                                Card(
                                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                                    elevation = 8.dp
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(text = marker.title)
                                        Text(text = marker.snippet)
                                    }
                                }
                            }
                        }
                    }
                }*/
            }
        }
    )
}

@Composable
fun TopBar(search:String, onSearchChange: (String) -> Unit) {
    Row(
        modifier=Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = {onSearchChange},
            label = { Text("Buscar") },
            placeholder = {
                Text(
                    "Buscar parque..."
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Buscar"
                    )
                }
            }
        )
    }
}