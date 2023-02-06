package com.example.tinpet.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun LoginScreen(
    onClick: () -> Unit
){
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var pwst = true
    val maxChar = 9
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {

            // LOGO SUPERIOR
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Logo TinPet
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        //.background(color = MaterialTheme.colors.primaryVariant),
                        contentAlignment = Alignment.Center
                        //horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Image(
                            modifier = Modifier
                                .size(64.dp)
                                .padding(8.dp),
                            painter = painterResource(R.drawable.icon_pawprint),
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
            }
            // INICIO DE SESIÓN
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text(
                        modifier = Modifier.padding(5.dp),
                        textAlign = TextAlign.Center,
                        text = (stringResource(R.string.login_ES)),
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            // USER NAME
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Nombre del usuario
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            if (it.length <= maxChar) name = it
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.username_ES),
                                color = MaterialTheme.colors.onBackground
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.phone_ES),
                                color = MaterialTheme.colors.onBackground
                            )
                        },
                        leadingIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.PhoneAndroid,
                                    tint = MaterialTheme.colors.onBackground,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation =
                        if (isSystemInDarkTheme()) {
                            PrefixVisualTransformationDark("+34 | ")
                        }else{
                            PrefixVisualTransformationLight("+34 | ")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
                    )
                }
                // USER PASSWORD
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Contraseña del usuario
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                text = stringResource(R.string.password_ES),
                                color = MaterialTheme.colors.onBackground
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.pswd_text_ES),
                                color = MaterialTheme.colors.onBackground
                            )
                        },
                        trailingIcon = {
                            if (pwst) {
                                IconButton(
                                    onClick = { pwst = false }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Visibility,
                                        tint = MaterialTheme.colors.onBackground,
                                        contentDescription = null,
                                    )
                                }
                            } else {
                                IconButton(
                                    onClick = { pwst = true }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.VisibilityOff,
                                        tint = Color.Red,
                                        contentDescription = null,
                                    )
                                }
                            }
                        },
                        leadingIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.Password,
                                    tint = MaterialTheme.colors.onBackground,
                                    contentDescription = null,
                                )
                            }
                        },
                        visualTransformation =
                        if (pwst) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        }
                    )
                }
                Spacer(Modifier.size(10.dp))
                // BOTON ACCEDER
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { onClick() },
                        //enabled = password.length > 9 && name.length == 9,
                        shape = RoundedCornerShape(25),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)

                    ) {

                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )

                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = stringResource(R.string.login_access_ES),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun LoginScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        LoginScreen(onClick = {})
    }
}

@Composable
@Preview
fun LoginScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        LoginScreen(onClick = {})
    }
}
class PrefixVisualTransformationDark(private val prefix: String) : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val transformedText = AnnotatedString(
                prefix,
                SpanStyle(Color.White)
            ) + text

            return TransformedText(transformedText, PrefixOffsetMapping(prefix))
        }

}
class PrefixVisualTransformationLight(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = AnnotatedString(
            prefix,
            SpanStyle(Color.Black)
        ) + text

        return TransformedText(transformedText, PrefixOffsetMapping(prefix))
    }

}

class PrefixOffsetMapping(private val prefix: String) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset + prefix.length

    override fun transformedToOriginal(offset: Int): Int {
        val delta = offset - prefix.length
        return if (delta < 0) 0 else delta
    }
}