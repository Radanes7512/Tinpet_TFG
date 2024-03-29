package com.example.tinpet.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.UiState
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.LoginViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel, onClick: () -> Unit, onRegClick: () -> Unit
) {
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val context = LocalContext.current
    when (viewModel.uiState.value) {
        is UiState.SignIn -> onClick()
        else -> {}
    }
    Scaffold(content = {
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
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
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
            // LOGIN COMPONENTS
            Login(Modifier, viewModel, onRegClick = { onRegClick() })

            // BOTÓN ACCEDER
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loginEnable) {
                    Button(
                        onClick = {
                            viewModel.login(context)
                        },
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
                            imageVector = Icons.Filled.LockOpen,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )

                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = stringResource(R.string.login_access_ES)
                        )
                    }
                } else {
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
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = stringResource(R.string.login_access_ES)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = stringResource(R.string.signup_access_ES),
                    modifier = Modifier.clickable { onRegClick() },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) {
                        Color(0xFFFFFFFF)
                    } else {
                        Color(0xFFFB9600)
                    }
                )
            }
        }
    })
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, onRegClick: () -> Unit) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val validEmail: Boolean = email.let { viewModel.isValidEmail(it) }

    val password: String by viewModel.password.observeAsState(initial = "")
    val validPass: Boolean = password.let { viewModel.isValidPassword(it) }


    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(15.dp))
        LTitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        LUserField(email, validEmail) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(5.dp))
        LPasswordField(password, validPass) { viewModel.onLoginChanged(email, it) }
        Spacer(modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.padding(15.dp))
    }
}

@Composable
fun LTitleText(modifier: Modifier) {
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

@Composable
fun LPasswordField(password: String, validPass: Boolean, onTextFieldChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Contraseña del usuario
        OutlinedTextField(value = password, isError = if (password.isEmpty()) {
            false
        } else {
            !validPass
        }, singleLine = true, onValueChange = { onTextFieldChanged(it) }, label = {
            if (validPass) {
                Text(
                    text = "${password.length}/20 | Contraseña válida", color = MaterialTheme.colors.secondary
                )
            } else {
                if (password.isEmpty()) {
                    Text(
                        text = stringResource(R.string.password_ES),
                        color = MaterialTheme.colors.onBackground
                    )
                } else {
                    Text(
                        text = "${password.length}/20 | Contraseña no válida", color = MaterialTheme.colors.error
                    )
                }
            }
        }, placeholder = {
            Text(
                text = stringResource(R.string.pswd_text_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        tint = Color.Red,
                        contentDescription = null,
                    )
                }
            } else {
                IconButton(onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null,
                    )

                }
            }
        }, leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Password,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null,
                )
            }
        }, visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        )
        )
    }
}

@Composable
fun LUserField(email: String, validEmail: Boolean, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(
            value = email,
            isError = if (email.isEmpty()) {
                false
            } else {
                !validEmail
            },
            singleLine = true,
            onValueChange = {
                onTextFieldChanged(it)
            },
            label = {
                if (validEmail) {
                    Text(
                        text = "Email válido", color = MaterialTheme.colors.secondary
                    )
                } else {
                    if (email.isEmpty()) {
                        Text(
                            text = stringResource(R.string.mail_ES),
                            color = MaterialTheme.colors.onBackground
                        )
                    } else {
                        Text(
                            text = "Se requiere un email válido", color = MaterialTheme.colors.error
                        )
                    }
                }

            },
            placeholder = {
                Text(
                    text = stringResource(R.string.mail_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next // Establecer el botón del teclado como "Siguiente"
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground),
        )
    }
}