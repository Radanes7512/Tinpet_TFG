package com.example.tinpet.screens.mainMenu
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlacesScreen() {
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.SATELLITE, isMyLocationEnabled = true)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }
    val spain = LatLng(39.53721928672391, -3.416821204546524)
    var showDialog by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf<LatLng?>(null) }
    var title by remember { mutableStateOf("") }
    var snippet by remember { mutableStateOf("") }
    var cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(spain, 5.5f)
    }
    val defaultMarkers = listOf(
        MarkerOptions()
            .position(LatLng(40.421290016336684, -3.543930244461727))
            .title("Parque la Colina")
            .snippet("Zona muy transcurrida"),
        MarkerOptions()
            .position(LatLng(40.46036862008319, -3.450723624671722))
            .title("Parque de la Mancha Amarilla")
            .snippet("Zona transcurrida"),
        MarkerOptions()
            .position(LatLng(40.42373493132333, -3.53992243227222))
            .title("Parque canino")
            .snippet("Zona poco transcurrida"),
        MarkerOptions()
            .position(LatLng(22.678290518593332, -73.82836630373427))
            .title("Avión hundido")
            .snippet("Triángulo de las Bermudas")
    )
    var markers by remember { mutableStateOf(defaultMarkers) }

    Scaffold(
        topBar = {
            TopBar(
                search,
                onSearchChange = { newSearch ->search = newSearch}
            )
        },
        content = {

            Row(
                modifier=Modifier
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
                            Column {
                                TextField(
                                    value = title,
                                    onValueChange = { newTitle -> title = newTitle },
                                    label = { Text("Título") },
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    )

                                )
                                TextField(
                                    value = snippet,
                                    onValueChange = { newSnippet -> snippet = newSnippet },
                                    label = { Text("Etiqueta") },
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Go
                                    )
                                )
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val newMarker = MarkerOptions()
                                        .position(selectedLatLng ?: LatLng(0.0, 0.0)) // Nueva posición del marcador
                                        .title(title)
                                        .snippet(snippet)
                                    markers = markers + newMarker
                                    Toast.makeText(
                                        context,
                                        "Se ha añadido un nuevo parque",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    showDialog = false
                                    cameraPositionState.position= CameraPosition.fromLatLngZoom(selectedLatLng!!, 50f)
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
                    onMapLongClick = {
                            latLng ->
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

@Composable
fun TopBar(
    search:String,
    onSearchChange: (String) -> Unit
) {
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