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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.LoginViewModel
import java.io.File

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(
    viewModel: LoginViewModel, onClick: () -> Unit, onBackClick: () -> Unit
) {
    val signupEnable: Boolean by viewModel.signupEnable.observeAsState(initial = false)
    val addpetEnable: Boolean by viewModel.addpetEnable.observeAsState(initial = false)

    val context = LocalContext.current
    // Agregar un estado composable para almacenar la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
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
        // region CUERPO DEL REGISTRO
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                Text(
                                    text = stringResource(R.string.signup_access_ES)
                                )

                            }
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }
        })
}

@Composable
fun Signup(modifier: Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val validEmail: Boolean = email.let { viewModel.isValidEmail(it) }

    val name: String by viewModel.name.observeAsState(initial = "")
    val validName: Boolean = name.let { viewModel.isValidName(it) }

    val password: String by viewModel.password.observeAsState(initial = "")
    val validPass: Boolean = password.let { viewModel.isValidPassword(it) }

    val password2: String by viewModel.password2.observeAsState(initial = "")

    val validPass2: Boolean = password2 == password


    Column(modifier = modifier) {
        STitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        SUserField(email, validEmail) { viewModel.onSignupChanged(it, name, password, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        SUserName(name, validName) { viewModel.onSignupChanged(email, it, password, password2) }
        Spacer(modifier = Modifier.padding(5.dp))
        SPasswordField(password, validPass) {
            viewModel.onSignupChanged(
                email, name, it, password2
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        RepeatPassword(password2, validPass2) {
            viewModel.onSignupChanged(
                email, name, password, it
            )
        }
        Spacer(modifier = Modifier.padding(15.dp))
    }
}

@Composable
fun Addpet(
    modifier: Modifier,
    viewModel: LoginViewModel,
    selectedImageUri: Uri?,
    onImageSelected: (Uri) -> Unit,
) {
    val context = LocalContext.current
    val petname: String by viewModel.petname.observeAsState(initial = "")
    val validPetName: Boolean = petname.let { viewModel.isValidPetName(it) }

    val petage: String by viewModel.petage.observeAsState(initial = "")
    val validPetAge: Boolean = petage.let { viewModel.isValidPetAge(it) }

    val petimage: String by viewModel.petImageUri.observeAsState(initial = "")
    val validPetImage: Boolean = petimage.let { viewModel.isValidPetImage(it) }

    val petcategory: String by viewModel.petcategory.observeAsState(initial = "")
    val validPetCategory: Boolean = petcategory.let { viewModel.isValidPetCategory(it) }

    Column(modifier = modifier) {
        SAddpetText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        SPetName(petname, validPetName) {
            viewModel.onAddpetChanged(
                it, petage, petcategory, petimage
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        SPetAge(petage, validPetAge) {
            viewModel.onAddpetChanged(
                petname, it, petcategory, petimage
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        SPetCategory(
            petcategory,
            validPetCategory
        ) {
            viewModel.onAddpetChanged(
                petname, petage, it, petimage
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        SPetImage(
            petimage,
            validPetImage,
            viewModel
        ) {
            viewModel.onAddpetChanged(
                petname, petage, petcategory, it.toString()
            )
        }
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
fun SPetName(petname: String, validPetName: Boolean, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre de la mascota
        OutlinedTextField(value = petname, isError = if (petname.isEmpty()) {
            false
        } else {
            !validPetName
        }, singleLine = true,
            onValueChange = {
                onTextFieldChanged(it)
            }, label = {
                if (validPetName) {
                    Text(
                        text = "${petname.length}/10 | Nombre válido ", color = MaterialTheme.colors.secondary
                    )
                } else {
                    if (petname.isEmpty()) {
                        Text(
                            text = stringResource(R.string.petname_ES),
                            color = MaterialTheme.colors.onBackground
                        )
                    } else {
                        Text(
                            text = "${petname.length}/10 | Debe tener entre 2 y 10 caracteres ",
                            color = MaterialTheme.colors.error
                        )
                    }
                }
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
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ), colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}

@Composable
fun SPetAge(petage: String, validPetAge: Boolean, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(value = petage, isError = if (petage.isEmpty()) {
            false
        } else {
            !validPetAge
        }, singleLine = true, onValueChange = {
            onTextFieldChanged(it)
        }, label = {
            if (validPetAge) {
                Text(
                    text = "Edad válida", color = MaterialTheme.colors.secondary
                )
            } else {
                if (petage.isEmpty()) {
                    Text(
                        text = stringResource(R.string.petage_ES),
                        color = MaterialTheme.colors.onBackground
                    )
                } else {
                    Text(
                        text = "Debe estar entre 1 y 30 años",
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }, placeholder = {
            Text(
                text = stringResource(R.string.page_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Cake,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null
                )
            }
        }, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
        ), colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}

@Composable
fun SPetImage(
    petimage: String,
    validPetImage: Boolean,
    viewModel: LoginViewModel,
    onImageSelected: (Uri) -> Unit
) {
    // Agregar un estado composable para almacenar la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val deletePhotoConfirm = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val photoFile = File.createTempFile(
        "photo", /* prefix */
        ".jpg", /* suffix */
        context.externalCacheDir /* directory */
    )

    val photoUri = FileProvider.getUriForFile(
        context,
        "com.example.tinpet.fileprovider",
        photoFile
    )
    // Llamar a rememberLauncherForActivityResult para obtener el resultado de la selección de la imagen
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            onImageSelected(it)
            viewModel.setImage(it)
        }
    }
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            selectedImageUri = photoUri
            selectedImageUri?.let {
                onImageSelected(it)
                viewModel.setImage(it)
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Añade una foto de tu mascota",
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
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
                        painter = rememberAsyncImagePainter(
                            selectedImageUri
                        ),
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
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        disabledBackgroundColor = MaterialTheme.colors.onSurface,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PhotoLibrary,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Galería")
                    }
                }
                Button(
                    onClick = {
                        takePictureLauncher.launch(photoUri)
                    },
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        disabledBackgroundColor = MaterialTheme.colors.onSurface,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AddAPhoto,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Cámara")
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
                    TextButton(
                        modifier = Modifier.padding(10.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.error),
                        shape = RoundedCornerShape(size = 30.dp),
                        //elevation = 10,
                        onClick = {
                            selectedImageUri = null
                            onImageSelected(Uri.EMPTY)
                            viewModel.setImage(Uri.EMPTY)
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
fun SPetCategory(
    petcategory: String,
    validPetCategory: Boolean,
    onCategorySelected: (String) -> Unit
) {
    val items = listOf(
        stringResource(R.string.peaceful_ES),
        stringResource(R.string.playful_ES),
        stringResource(R.string.eater_ES),
        stringResource(R.string.sleepyhead_ES),
        stringResource(R.string.nervous_ES),
        stringResource(R.string.aggressive_ES),
        stringResource(R.string.protective_ES),
        stringResource(R.string.loyal_ES),
        stringResource(R.string.affectionate_ES),
        stringResource(R.string.intelligent_ES),
        stringResource(R.string.obedient_ES),
        stringResource(R.string.curious_ES)
    )
    val maxSelected = 1
    val columns = when {
        items.size > 8 -> 3
        items.size > 4 -> 2
        else -> 1
    }
    val itemsPerColumn = (items.size + columns - 1) / columns
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Dinos cómo es tu mascota",
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
        Row {
            for (column in 0 until columns) {
                Column {
                    val columnItems = items.subList(
                        column * itemsPerColumn,
                        minOf((column + 1) * itemsPerColumn, items.size)
                    )
                    columnItems.forEachIndexed { index, item ->
                        val itemIndex = column * itemsPerColumn + index
                        Card(
                            elevation = 8.dp,
                            backgroundColor = when {
                                selectedIndex.value == itemIndex -> MaterialTheme.colors.secondary
                                else -> MaterialTheme.colors.primary
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .size(width = 80.dp, height = 40.dp)
                                .alpha(if (selectedIndex.value == null || selectedIndex.value == itemIndex) 1f else 0.5f)
                                .clickable(
                                    enabled = selectedIndex.value == null || selectedIndex.value == itemIndex
                                ) {
                                    selectedIndex.value =
                                        if (selectedIndex.value == itemIndex) null else itemIndex
                                    val category = if (selectedIndex.value == null) "" else item
                                    onCategorySelected(category)
                                }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SUserName(name: String, validName: Boolean, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(value = name, isError = if (name.isEmpty()) {
            false
        } else {
            !validName
        }, singleLine = true, onValueChange = {
            onTextFieldChanged(it)
        }, label = {
            if (validName) {
                Text(
                    text = "${name.length}/20 | Usuario válido", color = MaterialTheme.colors.secondary
                )
            } else {
                if (name.isEmpty()) {
                    Text(
                        text = stringResource(R.string.username_ES),
                        color = MaterialTheme.colors.onBackground
                    )
                } else {
                    Text(
                        text = "${name.length}/20 | Debe tener entre 2 y 20 caracteres",
                        color = MaterialTheme.colors.error
                    )
                }
            }
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
        }, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ), colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
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
fun RepeatPassword(password2: String, validPass2: Boolean, onTextFieldChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Contraseña del usuario
        OutlinedTextField(value = password2, isError = if (password2.isEmpty()) {
            false
        } else {
            !validPass2
        }, singleLine = true, onValueChange = { onTextFieldChanged(it) }, label = {
            if (password2.isEmpty()) {
                Text(
                    text = stringResource(R.string.repeat_password_ES),
                    color = MaterialTheme.colors.onBackground
                )
            } else {
                if (validPass2) {
                    Text(
                        text = "Las contraseñas son iguales",
                        color = MaterialTheme.colors.secondary
                    )
                } else {
                    Text(
                        text = "Las contraseñas no coinciden",
                        color = MaterialTheme.colors.error
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
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
        )
        )
    }
}

@Composable
fun SPasswordField(password: String, validPass: Boolean, onTextFieldChanged: (String) -> Unit) {
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
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
        )
        )


    }
}

@Composable
fun SUserField(email: String, validEmail: Boolean, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(value = email, isError = if (email.isEmpty()) {
            false
        } else {
            !validEmail
        }, singleLine = true, onValueChange = {
            onTextFieldChanged(it)
        }, label = {
            if (validEmail) {
                Text(
                    text = "Email válido", color = MaterialTheme.colors.secondary
                )
            } else {
                if (email.isEmpty()) {
                    Text(
                        text = stringResource(R.string.user_mail_ES),
                        color = MaterialTheme.colors.onBackground
                    )
                } else {
                    Text(
                        text = "Se requiere un email válido", color = MaterialTheme.colors.error
                    )
                }
            }
        }, placeholder = {
            Text(
                text = stringResource(R.string.mail_ES),
                color = MaterialTheme.colors.onBackground
            )
        }, leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null
                )
            }
        }, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ), colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}