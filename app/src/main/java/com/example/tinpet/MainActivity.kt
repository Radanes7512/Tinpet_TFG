package com.example.tinpet

import android.content.pm.PackageManager
import android.os.Bundle
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.graphs.MainNavigationGraph
import com.example.tinpet.ui.theme.TinPetTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {

    private val locationPermissionRequestCode = 100
    private val galleryPermissionRequestCode = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verificar si se han otorgado los permisos de ubicación
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permiso de ubicación
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionRequestCode
            )
        }
        // Verificar si se han otorgado los permisos de galería
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permiso de galería
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                galleryPermissionRequestCode
            )
        }

        setContent {
            TinPetTheme {
                MainNavigationGraph(navController = rememberNavController())
            }
        }
    }

    // Este método se llama cuando el usuario otorga o niega los permisos solicitados
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            locationPermissionRequestCode -> {
                // Si el permiso fue otorgado
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Hacer algo con el permiso de ubicación
                } else {
                    // El permiso fue denegado
                }
                return
            }
            galleryPermissionRequestCode -> {
                // Si el permiso fue otorgado
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Hacer algo con el permiso de galería
                } else {
                    // El permiso fue denegado
                }
                return
            }
        }
    }
}



