package com.example.tinpet.screens

import com.example.tinpet.screens.LoginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.screens.LUserField
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun AddPetScreen(
    viewModel: LoginViewModel,
    onClick:() -> Unit
) {
    val addpetEnable: Boolean by viewModel.addpetEnable.observeAsState(initial = false)
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
        // ADDPET COMPONENTS
        AddPet(Modifier,viewModel)

        // BOTÓN CREAR CUENTA
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(addpetEnable){
                Button(
                    onClick = { viewModel.autenticate() },
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
                        imageVector = Icons.Filled.Pets,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )

                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = stringResource(R.string.addpet_access_ES)
                    )
                }
            }else{
                Button(
                    onClick = {viewModel.autenticate()},
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
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }

        }
    }
}



@Composable
<<<<<<< HEAD:app/src/main/java/com/example/tinpet/screens/AddPet.kt
fun AddPet(modifier: Modifier, viewModel: LoginViewModel) {
    val un = viewModel.name
    val petname: String by viewModel.petname.observeAsState(initial = "")
    val petage: String by viewModel.petage.observeAsState(initial = "")
=======
fun Signup(modifier: Modifier, viewModel: LoginViewModel) {
    val number: String by viewModel.number.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val password2: String by viewModel.password2.observeAsState(initial = "")
    val verifyNumber: String by viewModel.verifyNumber.observeAsState(initial = "")

>>>>>>> 4b16aa425564d8bdb4ae883380888137c7a57a05:app/src/main/java/com/example/tinpet/screens/mainMenu/AddPet.kt

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(15.dp))
        ApTitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
<<<<<<< HEAD:app/src/main/java/com/example/tinpet/screens/AddPet.kt
        ApPetName(petname) {viewModel.onAddpetChanged(it, petage )}
=======
        SUserField(verifyNumber) { viewModel.onVerifyNumberChanged(it) }
>>>>>>> 4b16aa425564d8bdb4ae883380888137c7a57a05:app/src/main/java/com/example/tinpet/screens/mainMenu/AddPet.kt
        Spacer(modifier = Modifier.padding(5.dp))
        ApPetAge(petage) { viewModel.onAddpetChanged(petname, it )}
        Spacer(modifier = Modifier.padding(5.dp))
        Spacer(modifier = Modifier.padding(15.dp))
    }
}

@Composable
fun ApTitleText(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center,
            text = (stringResource(R.string.addpet_ES)),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
<<<<<<< HEAD:app/src/main/java/com/example/tinpet/screens/AddPet.kt
fun ApPetAge(petage: String, onTextFieldChanged: (String) -> Unit) {
=======
fun SUserField(number: String, onTextFieldChanged: (String) -> Unit) {
>>>>>>> 4b16aa425564d8bdb4ae883380888137c7a57a05:app/src/main/java/com/example/tinpet/screens/mainMenu/AddPet.kt
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(
            value = petage,
            onValueChange = {
<<<<<<< HEAD:app/src/main/java/com/example/tinpet/screens/AddPet.kt
                onTextFieldChanged(it)
=======
                //if (it.length <= 9)
                    onTextFieldChanged(it)
>>>>>>> 4b16aa425564d8bdb4ae883380888137c7a57a05:app/src/main/java/com/example/tinpet/screens/mainMenu/AddPet.kt
            },
            label = {
                Text(
                    text = stringResource(R.string.petage_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.page_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Cake,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}

@Composable
fun ApPetName(petname: String, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(
            value = petname,
            onValueChange = {
                    onTextFieldChanged(it)
            },
            label = {
                Text(
                    text = stringResource(R.string.petname_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.pname_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
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
@Preview(showBackground = true, showSystemUi = true)
fun AddPetScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        AddPetScreen(viewModel = LoginViewModel(LocalContext.current),onClick = {})
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddPetScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        AddPetScreen(viewModel = LoginViewModel(LocalContext.current),onClick = {})
    }
}


