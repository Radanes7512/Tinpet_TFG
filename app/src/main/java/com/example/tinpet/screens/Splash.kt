package com.example.tinpet.screens

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tinpet.AppScreens
import com.example.tinpet.R
import com.example.tinpet.graphs.Graph
import com.example.tinpet.ui.theme.abrilFatface
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(navController:NavHostController) {
    val context = LocalContext.current
    val internetPermissionState = rememberPermissionState(Manifest.permission.ACCESS_NETWORK_STATE)

    if (internetPermissionState.status.isGranted) {
        // El permiso de Internet ya está concedido, continuar con la pantalla de bienvenida
        LaunchedEffect(key1 = true){
            delay(3000)
            navController.popBackStack()
            navController.navigate(AppScreens.Index.route)
        }
        Splash()
    } else if (internetPermissionState.status.shouldShowRationale) {
        // El permiso de Internet ha sido denegado previamente, pero se puede mostrar una explicación al usuario.
        // Por ejemplo, puede mostrar un diálogo que explique por qué se necesita el permiso de Internet.
        // Una vez que el usuario acepta, el estado del permiso cambia y se llama de nuevo a esta función composable.
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Permiso de Internet necesario") },
            text = { Text("Se necesita el permiso de Internet para acceder a los datos en línea. Por favor, otorgue el permiso en la configuración de la aplicación.") },
            confirmButton = {
                TextButton(onClick = {
                    internetPermissionState.launchPermissionRequest()
                }) {
                    Text("Aceptar")
                }
            }
        )
    } else {
        // El permiso de Internet no se ha concedido, por lo que se lanza una solicitud de permiso.
        // Una vez que el usuario otorga o deniega el permiso, el estado del permiso cambia y se llama de nuevo a esta función composable.
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permiso concedido, continuar con la pantalla de bienvenida
                navController.popBackStack()
                navController.navigate(AppScreens.Index.route)
            } else {
                // Permiso denegado, volver a mostrar la pantalla de solicitud de permiso.
                Toast.makeText(context, "Permiso de Internet denegado", Toast.LENGTH_SHORT).show()
            }
        }
        LaunchedEffect(key1 = true) {
            launcher.launch(Manifest.permission.INTERNET)
        }
    }
}



@Composable
fun Splash() {
    val infiniteTransition = rememberInfiniteTransition()
    val logoSize by infiniteTransition.animateFloat(
        initialValue = 100.0f,
        targetValue = 250.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, delayMillis = 100, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image(
           modifier = Modifier
               .size(logoSize.dp)
               .padding(8.dp),
           painter = painterResource(R.drawable.icon_pawprint)
           /*painter = if (isSystemInDarkTheme()) {
               painterResource(R.drawable.icon_pawprint_black)
           } else {
               painterResource(R.drawable.icon_pawprint_white)
           }*/,
           contentDescription = null,
           )
        Text(
            style = TextStyle(
                shadow = Shadow(
                    color = Color.DarkGray,
                    offset = Offset(2.0f, 5.0f),
                    blurRadius = 2f
                )
            ),
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}
