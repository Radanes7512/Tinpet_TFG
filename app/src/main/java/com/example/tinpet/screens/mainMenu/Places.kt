package com.example.tinpet.screens.mainMenu

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlacesScreen() {
    val spain = stringResource(R.string.spain_ES)
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }
    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.SATELLITE,
                isMyLocationEnabled = true
            )
        )
    }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }
    val countriesList = listOf(
        stringResource(R.string.spain_ES) to LatLng(39.53721928672391, -3.416821204546524),
        stringResource(R.string.italy_ES) to LatLng(42.976648277417304, 12.588910524210682),
        stringResource(R.string.france_ES) to LatLng(46.61653253544342, 1.7130195434187618),
        stringResource(R.string.germany_ES) to LatLng(51.14671334331841, 10.289255887839838),
        stringResource(R.string.uk_ES) to LatLng(54.31979852479028, -2.0524484488028705)
    )

    val countries = remember {
        mutableStateListOf(*countriesList.toTypedArray())
    }
    val defaultPosition = countries.first().second
    val selectedCountry = remember { mutableStateOf(countries.first()) }
    val expanded = remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf<LatLng?>(null) }
    var title by remember { mutableStateOf("") }
    var snippet by remember { mutableStateOf("") }
    val selectedRadioButton = remember { mutableStateOf(0) }
    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultPosition, 5.5f)
    }
    val defaultMarkers = listOf(
        MarkerOptions()
            .position(LatLng(40.421290016336684, -3.543930244461727))
            .title("Parque la Colina")
            .snippet("Muy transcurrido"),
        MarkerOptions()
            .position(LatLng(40.46036862008319, -3.450723624671722))
            .title("Parque de la Mancha Amarilla")
            .snippet("Transcurrido"),
        MarkerOptions()
            .position(LatLng(40.42373493132333, -3.53992243227222))
            .title("Parque canino")
            .snippet("Poco transcurrido"),
        MarkerOptions()
            .position(LatLng(22.678290518593332, -73.82836630373427))
            .title("Avión hundido")
            .snippet("Triángulo de las Bermudas")
    )
    var markers by remember { mutableStateOf(defaultMarkers) }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //https://www.flaticon.es/packs/european-circular-flags
                //https://convertio.co/es/
                //Icon(painter = painterResource(id = R.drawable.icon_pawprint_black), contentDescription = null)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Elegir país")
                    OutlinedButton(
                        onClick = { expanded.value = true },
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Text(text = selectedCountry.value.first)
                    }
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(onClick = {
                            selectedCountry.value = country
                            cameraPositionState.position =
                                CameraPosition.fromLatLngZoom(country.second, 5.5f)
                            expanded.value = false
                        }) {
                            Text(text = country.first)
                        }
                    }
                }
                OutlinedTextField(
                    value = search,
                    onValueChange = { onSearchChange -> search = onSearchChange},
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
        },
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        title = { Text("Nueva ubicación\n") },
                        text = {
                            Column (
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                TextField(
                                    value = title,
                                    onValueChange = { newTitle -> title = newTitle },
                                    label = { Text("Título") },
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    )
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ){
                                    Row (
                                        Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ){
                                        RadioButton(
                                            selected = selectedRadioButton.value == 1,
                                            onClick = {
                                                selectedRadioButton.value = 1
                                                snippet = "Muy transcurrido"
                                            }
                                        )
                                        Text("Muy transcurrido")
                                    }
                                    Row (
                                        Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween) {
                                        RadioButton(
                                            selected = selectedRadioButton.value == 2,
                                            onClick = {
                                                selectedRadioButton.value = 2
                                                snippet = "Transcurrido"
                                            }
                                        )
                                        Text("Transcurrido")
                                    }
                                    Row (
                                        Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        RadioButton(
                                            selected = selectedRadioButton.value == 3,
                                            onClick = {
                                                selectedRadioButton.value = 3
                                                snippet = "Poco transcurrido"
                                            }
                                        )
                                        Text("Poco transcurrido")
                                    }
                                }
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val newMarker = MarkerOptions()
                                        .position(
                                            selectedLatLng ?: LatLng(
                                                0.0,
                                                0.0
                                            )
                                        ) // Nueva posición del marcador
                                        .title(title)
                                        .snippet(snippet)
                                    markers = markers + newMarker
                                    Toast.makeText(
                                        context,
                                        "Se ha añadido un nuevo parque",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    showDialog = false
                                    cameraPositionState.position =
                                        CameraPosition.fromLatLngZoom(selectedLatLng!!, 50f)
                                }
                            ) {
                                Text("Añadir")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 100.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = properties,
                    uiSettings = uiSettings,
                    onMapLongClick = { latLng ->
                        selectedLatLng = latLng
                        showDialog = true
                    }
                ) {
                    markers.forEach { markerOptions ->
                        Marker(
                            position = markerOptions.position,
                            title = markerOptions.title,
                            snippet = markerOptions.snippet
                        )
                    }
                }
            }
        }
    )
}