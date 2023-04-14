package com.example.tinpet.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(
    viewModel: LoginViewModel,
    onClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val signupEnable: Boolean by viewModel.signupEnable.observeAsState(initial = false)
    val addpetEnable: Boolean by viewModel.addpetEnable.observeAsState(initial = false)

    // Agregar un estado composable para almacenar la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        //region BARRA SUPERIOR CON NOMBRE DE MASCOTA Y FELCHA PARA IR ATRÁS
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Registro", fontFamily = abrilFatface
                )
            }, navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        Icons.Filled.ArrowBack, contentDescription = null
                    )
                }
            })
        },
        //endregion
        // region CUERPO DEL CHAT DONDE SE VAN AÑADIENDO LOS MENSAJES ENVIADOS
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // LOGO SUPERIOR
                /*Row(
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
                }*/
                // SIGNUP COMPONENTS
                item {
                    Signup(Modifier, viewModel)
                }
                // ADDPET COMPONENTS
                item {
                    // Pasar selectedImageUri como una variable a AddPet
                    Addpet(Modifier, viewModel, selectedImageUri) {
                        selectedImageUri = it
                    }
                }
                // BOTÓN CREAR CUENTA
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (signupEnable && addpetEnable) {
                            Button(
                                onClick = {
                                    onClick()
                                    viewModel.register(context)
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
                            }
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        /*Text(
                            text = stringResource(R.string.login_ES),
                            modifier = Modifier.clickable { onBackClick() },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSystemInDarkTheme()) {
                                Color(0xFFFFFFFF)
                            } else {
                                Color(0xFFFB9600)
                            }
                        )*/
                    }
                }
            }
        }
    )
}


@Composable
fun Signup(modifier: Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val password2: String by viewModel.password2.observeAsState(initial = "")

    Column(modifier = modifier) {
        STitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        SUserField(email) { viewModel.onSignupChanged(it, name, password, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        SUserName(name) { viewModel.onSignupChanged(email, it, password, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        SPasswordField(password) { viewModel.onSignupChanged(email, name, it, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        RepeatPassword(password2) { viewModel.onSignupChanged(email, name, password, it) }
        Spacer(modifier = Modifier.padding(15.dp))
    }
}

@Composable
fun Addpet(
    modifier: Modifier,
    viewModel: LoginViewModel,
    selectedImageUri: Uri?,
    onImageSelected: (Uri) -> Unit
) {
    val petname: String by viewModel.petname.observeAsState(initial = "")
    val petage: String by viewModel.petage.observeAsState(initial = "")
    val petimage: Uri by viewModel.petImageUri.observeAsState(initial = Uri.EMPTY)
    var petcategory by remember { mutableStateOf("Option 1") }

    Column(modifier = modifier) {
        SAddpetText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        SPetName(petname) { viewModel.onAddpetChanged(it, petage, petcategory, petimage) }
        Spacer(modifier = Modifier.padding(5.dp))
        SPetAge(petage) { viewModel.onAddpetChanged(petname, it, petcategory, petimage) }
        Spacer(modifier = Modifier.padding(5.dp))
        SPetCategory(petcategory) { newCategory -> petcategory = newCategory }
        Spacer(modifier = Modifier.padding(5.dp))
        SPetImage(petimage, modifier = Modifier, onImageSelected = { uri -> onImageSelected(uri) })
    }
}

@Composable
fun SAddpetText(align: Modifier) {
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
fun SPetName(
    petname: String, onTextFieldChanged: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(value = petname, onValueChange = {
            onTextFieldChanged(it)
        }, label = {
            Text(
                text = stringResource(R.string.petname_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, placeholder = {
            Text(
                text = stringResource(R.string.pname_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null
                )
            }
        }, colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}

@Composable
fun SPetAge(petage: String, onTextFieldChanged: (String) -> Unit) {
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
                onTextFieldChanged(it)
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
fun SPetImage(petimage: Uri, modifier: Modifier = Modifier, onImageSelected: (Uri) -> Unit) {
    // Agregar un estado composable para almacenar la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val deletePhotoConfirm = remember { mutableStateOf(false) }

    // Llamar a rememberLauncherForActivityResult para obtener el resultado de la selección de la imagen
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            onImageSelected(it)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.padding(vertical = 16.dp),
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                disabledBackgroundColor = MaterialTheme.colors.onSurface,
                contentColor = Color.Black,
                disabledContentColor = Color.White
            )
        ) {
            Text("Añadir foto")
        }
        if (selectedImageUri != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier, contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .alpha(0.8f) // Agregar transparencia
                    )
                    IconButton(
                        onClick = { deletePhotoConfirm.value = true },
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = null, tint = Color.Red)
                    }
                }

            }
        }
    }
    if (deletePhotoConfirm.value) {
        AlertDialog(onDismissRequest = {
            deletePhotoConfirm.value = false
        },
            text = {
                Text(
                    text = "¿Desea borrar la foto?",
                    fontSize = 20.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
            },
            shape = RoundedCornerShape(size = 30.dp),
            backgroundColor = MaterialTheme.colors.background,
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(modifier = Modifier.padding(10.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        shape = RoundedCornerShape(size = 30.dp),
                        onClick = { deletePhotoConfirm.value = false }) {
                        Text(
                            text = "No",
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    TextButton(modifier = Modifier.padding(10.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.error),
                        shape = RoundedCornerShape(size = 30.dp),
                        //elevation = 10,
                        onClick = {
                            selectedImageUri = null
                            deletePhotoConfirm.value = false
                        }) {
                        Text(
                            text = "Eliminar", fontFamily = abrilFatface, color = Color.White
                        )
                    }
                }
            })
    }
}

@Composable
fun SPetCategory(petcategory: String, function: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Tranquilo", "Juguetón", "Comilón", "Dormilón")
    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Seleccionar categoría:")
        Box(
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .border(width = 1.dp, color = Color.Gray)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = items[selectedIndex],
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        )
        DropdownMenu(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    function(item)
                }) {
                    Text(text = item)
                }
            }
        }
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
        OutlinedTextField(value = name, onValueChange = {
            onTextFieldChanged(it)
        }, label = {
            Text(
                text = stringResource(R.string.username_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, placeholder = {
            Text(
                text = stringResource(R.string.name_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Person2,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null
                )
            }
        }, colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
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
        // Contraseña del usuario
        OutlinedTextField(value = password2, onValueChange = { onTextFieldChanged(it) }, label = {
            Text(
                text = stringResource(R.string.repeat_password_ES),
                color = MaterialTheme.colors.onBackground
            )
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
        // Contraseña del usuario
        OutlinedTextField(value = password, onValueChange = { onTextFieldChanged(it) }, label = {
            Text(
                text = stringResource(R.string.password_ES),
                color = MaterialTheme.colors.onBackground
            )
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
            prefix, SpanStyle(Color.White)
        ) + text

        return TransformedText(transformedText, SPrefixOffsetMapping(prefix))
    }

}


class SPrefixVisualTransformationLight(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text + AnnotatedString(
            prefix, SpanStyle(Color.Black)
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