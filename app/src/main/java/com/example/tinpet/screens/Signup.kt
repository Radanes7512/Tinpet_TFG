package com.example.tinpet.screens

import android.app.Activity
import android.renderscript.ScriptGroup.Binding
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun SignupScreen(
    viewModel: LoginViewModel,
    onClick:() -> Unit,
    onBackClick:() -> Unit
) {
    val signupEnable: Boolean by viewModel.signupEnable.observeAsState(initial = false)
    val context = LocalContext.current
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
        // SIGNUP COMPONENTS
        Signup(Modifier,viewModel)

        // BOT??N CREAR CUENTA
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(signupEnable){
                Button(
                    onClick = {
                        viewModel.register(context)
                       //onClick()
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
                        imageVector = Icons.Filled.DoneAll,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )

                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = stringResource(R.string.signup_access_ES)
                    )
                }
            }else{
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
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = stringResource(R.string.login_ES),
                modifier = Modifier.clickable { onBackClick() },
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
}



@Composable
fun Signup(modifier: Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val password2: String by viewModel.password2.observeAsState(initial = "")

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(15.dp))
        STitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        SUserField(email) { viewModel.onSignupChanged(it, name, password, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        SUserName(name){viewModel.onSignupChanged(email, it, password, password2)}
        Spacer(modifier = Modifier.padding(5.dp))
        SPasswordField(password) { viewModel.onSignupChanged(email, name, it, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        RepeatPassword(password2) { viewModel.onSignupChanged(email, name, password, it) }
        Spacer(modifier = Modifier.padding(15.dp))
    }
}

@Composable
fun SUserName(name: String, onTextFieldChanged: (String) -> Unit) {
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
                onTextFieldChanged(it)
            },
            label = {
                Text(
                    text = stringResource(R.string.username_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.name_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Person2,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}

@Composable
fun STitleText(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center,
            text = (stringResource(R.string.register_ES)),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun RepeatPassword(password2: String, onTextFieldChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Contrase??a del usuario
        OutlinedTextField(
            value = password2,
            onValueChange = { onTextFieldChanged(it) },
            label = {
                Text(
                    text = stringResource(R.string.repeat_password_ES),
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
                if (showPassword) {
                    IconButton(
                        onClick = { showPassword = false }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            tint = Color.Red,
                            contentDescription = null,
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            tint = MaterialTheme.colors.onBackground,
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
            if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
    }
}

@Composable
fun SPasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Contrase??a del usuario
        OutlinedTextField(
            value = password,
            onValueChange = { onTextFieldChanged(it) },
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
                if (showPassword) {
                    IconButton(
                        onClick = { showPassword = false }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            tint = Color.Red,
                            contentDescription = null,
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            tint = MaterialTheme.colors.onBackground,
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
            if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
    }
}

@Composable
fun SUserField(email: String, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(
            value = email,
            onValueChange = {
                onTextFieldChanged(it)
            },
            label = {
                Text(
                    text = stringResource(R.string.user_mail_ES),
                    color = MaterialTheme.colors.onBackground
                )
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}

//region FUNCIONES PREFIJO
class SPrefixVisualTransformationDark(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = AnnotatedString(
            prefix,
            SpanStyle(Color.White)
        ) + text

        return TransformedText(transformedText, SPrefixOffsetMapping(prefix))
    }

}


class SPrefixVisualTransformationLight(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text+ AnnotatedString(
            prefix,
            SpanStyle(Color.Black)
        ) + text

        return TransformedText(transformedText, SPrefixOffsetMapping(prefix))
    }

}
class SPrefixOffsetMapping(private val prefix: String) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset + prefix.length

    override fun transformedToOriginal(offset: Int): Int {
        val delta = offset - prefix.length
        return if (delta < 0) 0 else delta
    }
}
//endregion