package com.example.tinpet

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

class MainActivity : ComponentActivity() {
    private val requestPermissionsCode = 100
    private var showSet:Boolean  = false;
    private val permissionsToRequest = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    MainNavigationGraph(navController = rememberNavController())
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
    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
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
                // Al menos un permiso fue denegado
                showSet=true;
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
fun DefaultText(permissionsDenied: List<String>, requestPermissionsCode: Int, showSet:Boolean) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Box() {
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
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Row {
                Text(
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colors.onBackground,
                    text =
                    "Para poder utilizar esta aplicación, es necesario que aceptes" +
                            " todos los permisos que se soliciten. Sin estos permisos," +
                            " algunas funcionalidades podrían estar limitadas o no estar disponibles." +
                            "\n\n" +
                            "Asegúrate de aceptar todos los permisos necesarios para disfrutar" +
                            " de una experiencia completa. Si no estás seguro de qué permisos" +
                            " se están solicitando, revisa la lista y lee las descripciones cuidadosamente." +
                            "\n\n" +
                            "Si no otorga los permisos solicitados se le dará la opción de acceder a la configuración" +
                            " de la aplicación para que otorgue los permisos desde ahí o limpie el almacenamiento de la aplicación" +
                            " y volver a ejecutarla y así se puedan solicitar los permisos correctamente." +
                            "\n\n" +
                            "También puedes modificar los permisos más adelante en la configuración de la aplicación." +
                            "\n\n" +
                            "Recuerda que estamos comprometidos con proteger tu privacidad y seguridad," +
                            " y que estos permisos son necesarios para brindarte la mejor experiencia posible." +
                            "\n\n" +
                            "Gracias por confiar en nuestra aplicación." +
                            "\n\n\n" +
                            "Atentamente,"
                )
            }
            Spacer(modifier = Modifier.padding(vertical=16.dp))
            Text(
                text =  "El equipo técnico de "+ stringResource(
                    R.string.app_name
                ),
                fontFamily = abrilFatface,
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
        if(showSet){
            Button(
                onClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", "com.example.tinpet", null)
                    intent.data = uri
                    startActivity(context,intent,Bundle())
                },
                modifier = Modifier
                    .padding(bottom = 36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
                Text(
                    text = "Abrir ajustes"
                )
            }            
        }else{
            Button(
                onClick = {
                    ActivityCompat.requestPermissions(
                        activity,
                        permissionsDenied.toTypedArray(),
                        requestPermissionsCode
                    )
                },
                modifier = Modifier
                    .padding(bottom = 36.dp)
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

        Spacer(modifier = Modifier.padding(vertical=16.dp))
        Text(
            text = "© 2023",
            color = MaterialTheme.colors.onBackground
        )
    }
}
