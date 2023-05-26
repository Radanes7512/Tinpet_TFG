package com.example.tinpet.screens.mainMenu.profile

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.R
import com.example.tinpet.graphs.NavGraph
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.LoginViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController(),
    onPetClick: () -> Unit,
    onRqtClick: () -> Unit,
    viewModel: LoginViewModel
) {
    val text = viewModel.auth.currentUser?.email.toString()
    val splitText = text.split("@")
    val result = splitText[0]

    NavGraph(navController = navController)
    //region PROFILE CONTENT
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Nombre del usuario
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = result,
                        fontSize = 32.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                    Icon(imageVector = Icons.Default.Verified, contentDescription = "verified")
                }
            }

            item {
                // MIS MASCOTAS
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp), elevation = 10.dp

                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { onPetClick() }
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Mi mascota",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier.size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_pawprint),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
                // PETICIONES DE AMISTAD
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp), elevation = 10.dp

                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .clickable { onRqtClick() }
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(10.dp)) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = "Peticiones de amistad",
                            fontSize = 15.sp,
                            fontFamily = abrilFatface,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Image(
                            modifier = Modifier.size(25.dp, 25.dp),
                            painter = painterResource(R.drawable.icon_checkfriends),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        }
    }
}

