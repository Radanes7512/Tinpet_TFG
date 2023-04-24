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

    private val requestPermissionsCode = 100
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionsDenied = permissionsToRequest.filter {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED
        }
        if (permissionsDenied.isEmpty()) {
            // Todos los permisos fueron otorgados
            setContent {
                TinPetTheme {
                    MainNavigationGraph(navController = rememberNavController())
                }
            }
        } else {
            // Al menos un permiso fue denegado, solicitar permisos de nuevo
            ActivityCompat.requestPermissions(
                this,
                permissionsDenied.toTypedArray(),
                requestPermissionsCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestPermissionsCode) {
            val permissionsDenied = permissions.filterIndexed { index, _ ->
                grantResults[index] == PackageManager.PERMISSION_DENIED
            }
            if (permissionsDenied.isEmpty()) {
                // Todos los permisos fueron otorgados
                setContent {
                    TinPetTheme {
                        MainNavigationGraph(navController = rememberNavController())
                    }
                }
            } else {
                // Al menos un permiso fue denegado, volver a solicitar permisos
                ActivityCompat.requestPermissions(
                    this,
                    permissionsDenied.toTypedArray(),
                    requestPermissionsCode
                )
            }
        }
    }
}


