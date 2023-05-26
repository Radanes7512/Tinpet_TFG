package com.example.tinpet

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.graphs.MainNavigationGraph
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {

    private val requestPermissionsCode = 100
    private var showSet: Boolean = false
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )

    fun checkIfUserIsLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        return username != null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (!notificationManager.areNotificationsEnabled()) {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                startActivity(intent)
            }

        }*/

        val permissionsDenied = permissionsToRequest.filter {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED
        }
        val permissionsGranted = permissionsToRequest.filter {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

        if (permissionsDenied.isEmpty() && permissionsGranted.size == permissionsToRequest.size) {
            // Todos los permisos fueron otorgados
            setContent {
                TinPetTheme {
                    if (checkIfUserIsLoggedIn(this)) {
                        // Navega directamente al gráfico Graph.MAIN
                        MainNavigationGraph(
                            navController = rememberNavController(),
                            isLoggedIn = checkIfUserIsLoggedIn(this)
                        )
                    } else {
                        // Muestra el gráfico de autenticación
                        MainNavigationGraph(
                            navController = rememberNavController(),
                            isLoggedIn = checkIfUserIsLoggedIn(this)
                        )
                    }
                }
            }
        } else {
            setContent {
                TinPetTheme {
                    DefaultText(permissionsDenied, requestPermissionsCode, showSet)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
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
                        if (checkIfUserIsLoggedIn(this)) {
                            // Navega directamente al gráfico Graph.MAIN
                            MainNavigationGraph(
                                navController = rememberNavController(),
                                isLoggedIn = checkIfUserIsLoggedIn(this)
                            )

                        } else {
                            // Muestra el gráfico de autenticación
                            MainNavigationGraph(
                                navController = rememberNavController(),
                                isLoggedIn = checkIfUserIsLoggedIn(this)
                            )
                        }
                    }
                }
            } else {
                // Al menos un permiso fue denegado
                showSet = true
                setContent {
                    TinPetTheme {
                        DefaultText(permissionsDenied, requestPermissionsCode, showSet)
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultText(permissionsDenied: List<String>, requestPermissionsCode: Int, showSet: Boolean) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    Box(
        /*horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,*/
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp)
                    .padding(8.dp),
                painter = painterResource(R.drawable.icon_pawprint),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontFamily = abrilFatface,
                color = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            item {
                Row {
                    Text(
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colors.onBackground,
                        text =
                        "Para poder utilizar esta aplicación, es necesario que acepte" +
                                " todos los permisos que se le soliciten y así disfrute de una experiencia completa." +
                                " Sin ellos, algunas funcionalidades podrían estar limitadas o no estar disponibles." +
                                "\n\n" +
                                "Si no los otorga se le dará la opción de acceder a la configuración" +
                                " de la aplicación para que lo haga desde ahí o limpie el almacenamiento de la aplicación" +
                                " y vuelva a ejecutarla y se soliciten correctamente." +
                                "\n\n" +
                                "También puede modificar los permisos más adelante en la configuración de la aplicación." +
                                "\n\n" +
                                "Recuerde que estamos comprometidos con proteger su privacidad y seguridad," +
                                " y que estos permisos son necesarios para brindarle la mejor experiencia posible." +
                                "\n\n" +
                                "Gracias por confiar en nuestra aplicación." +
                                "\n\n\n" +
                                "Atentamente,"
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = "El equipo de " + stringResource(
                        R.string.app_name
                    ),
                    fontFamily = abrilFatface,
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            if (showSet) {
                Button(
                    onClick = {
                        /*Firebase.messaging.subscribeToTopic("Prueba")*/
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", "com.example.tinpet", null)
                        intent.data = uri
                        startActivity(context, intent, Bundle())
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                    Text(
                        text = "Abrir ajustes"
                    )
                }
            } else {
                Button(
                    onClick = {
                        ActivityCompat.requestPermissions(
                            activity,
                            permissionsDenied.toTypedArray(),
                            requestPermissionsCode
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null
                    )
                    Text(
                        text = "Aceptar"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "© 2023",
            color = MaterialTheme.colors.onBackground
        )
    }


}


