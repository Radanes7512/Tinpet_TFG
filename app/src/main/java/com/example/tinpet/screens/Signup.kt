package com.example.tinpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun SignupScreen(
    onClick:() -> Unit
) {
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
        // LOGIN COMPONENTS
        //Signup(Modifier,viewModel)

        // BOTÓN ACCEDER
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
                    imageVector = Icons.Filled.LockOpen,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(R.string.signup_access_ES)
                )
            }

        }
    }
}


/*
@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    val number: String by viewModel.number.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(15.dp))
        TitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        UserField(number) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(5.dp))
        PasswordField(password) { viewModel.onLoginChanged(number, it) }
        Spacer(modifier = Modifier.padding(10.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(15.dp))
    }
}
*/
/*
@Composable
fun TitleText(modifier: Modifier) {
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
*/
/*
@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Olvidaste la contraseña?",
        modifier = modifier
            .clickable { }
            .padding(10.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = if (isSystemInDarkTheme()) {
            Color(0xFFFFFFFF)
        } else {
            Color(0xFFFB9600)
        }
    )
}
*/
/*
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Contraseña del usuario
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
*/
/*
@Composable
fun UserField(number: String, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(
            value = number,
            onValueChange = {
                if (it.length <= 9)
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
            } else {
                PrefixVisualTransformationLight("+34 | ")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}
*/

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignupScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        SignupScreen(onClick = {})
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignupScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        SignupScreen(onClick = {})
    }
}
/*
class PrefixVisualTransformationDark(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = AnnotatedString(
            prefix,
            SpanStyle(Color.White)
        ) + text

        return TransformedText(transformedText, PrefixOffsetMapping(prefix))
    }

}
*/
/*
class PrefixVisualTransformationLight(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = AnnotatedString(
            prefix,
            SpanStyle(Color.Black)
        ) + text

        return TransformedText(transformedText, PrefixOffsetMapping(prefix))
    }

}
*/
/*
class PrefixOffsetMapping(private val prefix: String) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset + prefix.length

    override fun transformedToOriginal(offset: Int): Int {
        val delta = offset - prefix.length
        return if (delta < 0) 0 else delta
    }
}
*/