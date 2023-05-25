package com.example.tinpet.screens.mainMenu

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.tinpet.R
import com.example.tinpet.viewModels.PlacesViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlacesScreen(
    viewModel: PlacesViewModel
) {
    viewModel.loadPlaces()
    val context = LocalContext.current

    val title: String by viewModel.title.observeAsState(initial = "")
    val markers:List<MarkerOptions> by viewModel.markers.observeAsState(initial = listOf())

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
        //https://www.flaticon.es/packs/european-circular-flags
        Triple(
            painterResource(R.drawable.flag_spain),
            stringResource(R.string.spain_ES),
            LatLng(39.53721928672391, -3.416821204546524)
        ),
        Triple(
            painterResource(R.drawable.flag_italy),
            stringResource(R.string.italy_ES),
            LatLng(42.976648277417304, 12.588910524210682)
        ),
        Triple(
            painterResource(R.drawable.flag_france),
            stringResource(R.string.france_ES),
            LatLng(46.61653253544342, 1.7130195434187618)
        ),
        Triple(
            painterResource(R.drawable.flag_germany),
            stringResource(R.string.germany_ES),
            LatLng(51.14671334331841, 10.289255887839838)
        ),
        Triple(
            painterResource(R.drawable.flag_uk),
            stringResource(R.string.uk_ES),
            LatLng(54.31979852479028, -2.0524484488028705)
        ),
        Triple(
            painterResource(R.drawable.flag_usa),
            stringResource(R.string.usa_ES),
            LatLng(40.351174508864545, -101.63479453358795)
        ),
        Triple(
            painterResource(R.drawable.flag_china),
            stringResource(R.string.china_ES),
            LatLng(34.94650584858449, 103.52418053419156)
        ),
        Triple(
            painterResource(R.drawable.flag_arg),
            stringResource(R.string.arg_ES),
            LatLng(-34.89835307223945, -64.95531286103939)
        )
    )
    val countries = remember {
        mutableStateListOf(*countriesList.toTypedArray())
    }
    val defaultPosition = countries.first().third
    val selectedCountry = remember { mutableStateOf(countries.first()) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf<LatLng?>(null) }
    var isTitleEmpty by remember { mutableStateOf(false) }
    var isRBEmpty by remember {mutableStateOf(false)}
    val selectedRadioButton = remember { mutableStateOf(0) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultPosition, 5.5f)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = search,
                    onValueChange = { onSearchChange -> search = onSearchChange },
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
                Image(
                    painter = selectedCountry.value.first,
                    contentDescription = null,
                    Modifier
                        .size(25.dp)
                        .clickable { showDialog2 = !showDialog2 }
                )
            }
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 100.dp)
            ) {
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        title = { Text("Añadir ubicación") },
                        text = {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    //.background(MaterialTheme.colors.background)
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                OutlinedTextField(
                                    modifier=Modifier.padding(top=16.dp),
                                    value = title,
                                    onValueChange = {newTitle ->
                                        viewModel.setTitle(newTitle)
                                    },
                                    isError = isTitleEmpty,
                                    label = {
                                        if(!isTitleEmpty){
                                            Text("Nombre del parque")
                                        }else{
                                            Text(
                                                "No puede estar vacío",
                                                color=MaterialTheme.colors.error
                                            )
                                        }
                                    }
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        RadioButton(
                                            selected = selectedRadioButton.value == 1,
                                            onClick = {
                                                selectedRadioButton.value = 1
                                                viewModel.setSnippet("Muy transcurrido")
                                            }
                                        )
                                        Text("Muy transcurrido")
                                    }
                                    Row(
                                        Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        RadioButton(
                                            selected = selectedRadioButton.value == 2,
                                            onClick = {
                                                selectedRadioButton.value = 2
                                                viewModel.setSnippet("Transcurrido")
                                            }
                                        )
                                        Text("Transcurrido")
                                    }
                                    Row(
                                        Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        RadioButton(
                                            selected = selectedRadioButton.value == 3,
                                            onClick = {
                                                selectedRadioButton.value = 3
                                                viewModel.setSnippet("Poco transcurrido")
                                            }
                                        )
                                        Text("Poco transcurrido")
                                    }
                                }
                                if(isRBEmpty){
                                    Text("Se debe elegir una categoría", color=MaterialTheme.colors.error,modifier=Modifier.padding(8.dp))
                                }

                            }
                        },
                        backgroundColor = MaterialTheme.colors.background,
                        confirmButton = {
                            Button(
                                onClick = {
                                    if (title.isEmpty()) {
                                        isTitleEmpty = true
                                    } else {
                                        if (selectedRadioButton.value != 0) {
                                            /*val newMarker = MarkerOptions()
                                                .position(
                                                    selectedLatLng ?: LatLng(
                                                        0.0,
                                                        0.0
                                                    )
                                                ) // Nueva posición del marcador
                                                .title(title)
                                                .snippet(snippet)*/
                                            //markers = markers + newMarker
                                            Toast.makeText(
                                                context,
                                                "Se ha añadido un nuevo parque",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            showDialog = false
                                            cameraPositionState.position =
                                                CameraPosition.fromLatLngZoom(selectedLatLng!!, 20f)

                                            // Llamada a la función places del ViewModel
                                            viewModel.places()
                                            viewModel.loadPlaces()
                                        }else{
                                            isRBEmpty=true
                                        }
                                    }
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
                if (showDialog2) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Seleccionar país")
                        Spacer(modifier=Modifier.padding(8.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            countries.forEach { country ->
                                item {
                                    Card(
                                        backgroundColor = MaterialTheme.colors.background,
                                        elevation = 8.dp,
                                        modifier = Modifier
                                            .padding(8.dp)
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .clickable {
                                                    selectedCountry.value = country
                                                    cameraPositionState.position =
                                                        CameraPosition.fromLatLngZoom(
                                                            country.third,
                                                            5.5f
                                                        )
                                                    showDialog2 = false
                                                }
                                                .padding(8.dp)
                                        ) {
                                            Image(
                                                painter = country.first,
                                                contentDescription = null,
                                                modifier = Modifier.size(25.dp)
                                            )
                                            Text(text = country.second)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier=Modifier.padding(8.dp))
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = properties,
                    uiSettings = uiSettings,
                    onMapLongClick = { latLng ->
                        selectedLatLng = latLng
                        viewModel.setLatitude(latLng.latitude)
                        viewModel.setLongitude(latLng.longitude)
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