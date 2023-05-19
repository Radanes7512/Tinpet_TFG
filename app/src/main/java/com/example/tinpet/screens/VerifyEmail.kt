package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface


@Composable
fun VerifyEmailScreen(
    viewModel: LoginViewModel,
    onClick: () -> Unit
) {
    val emailVerified: Boolean by viewModel.emailVerified.observeAsState(initial = false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LOGO SUPERIOR
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primaryVariant)
        ) {
            // Logo TinPet
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
                //horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = if (isSystemInDarkTheme()) {
                        painterResource(R.drawable.icon_pawprint_black)
                    } else {
                        painterResource(R.drawable.icon_pawprint_white)
                    },
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 32.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
        // INPUTSMSNUM COMPONENTS
        EmailVerify(Modifier, viewModel)

        // BOTÓN VERIFICAR CODIGO
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //if (emailVerified) {
                Button(
                    onClick = { onClick() },
                    enabled = true,
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        disabledBackgroundColor = MaterialTheme.colors.onSurface,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )

                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = stringResource(R.string.vfdem_access_ES)
                    )
                }
           /* } else {
                Button(
                    onClick = {},
                    enabled = false,
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        disabledBackgroundColor = MaterialTheme.colors.onSurface,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            //}*/
        }
    }
}

@Composable
fun EmailVerify(modifier: Modifier, viewModel: LoginViewModel) {

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(15.dp))
        VETitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        VEBodyText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun VETitleText(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center,
            text = (stringResource(R.string.VFEM_ES)),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun VEBodyText(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Justify,
            text = "Estimado usuario," +
                    "\n\n" +
                    "Le informamos que para poder completar el proceso de registro en nuestro servicio," +
                    " es necesario que valide su cuenta. Se ha enviado un" +
                    " correo electrónico a la dirección proporcionada con un enlace" +
                    " de verificación. Por favor, revise su bandeja de entrada y siga las instrucciones." +
                    "\n\n" +
                    "Una vez verificado, podrá acceder a TinPet." +
                    " Si tiene alguna duda o problema, no dude en contactarnos." +
                    "\n\n\n" +
                    "Atentamente,"
        )
    }
    Spacer(modifier = Modifier.padding(vertical = 16.dp))
    Text(
        modifier = Modifier.padding(5.dp),
        text = "El equipo  de " + stringResource(R.string.app_name),
        fontFamily = abrilFatface,
        fontSize = 24.sp,
        color = MaterialTheme.colors.onBackground
    )

}
