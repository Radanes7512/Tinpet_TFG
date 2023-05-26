package com.example.tinpet.screens.mainMenu.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.ui.theme.abrilFatface
import com.example.tinpet.viewModels.ProfileViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PetProfileScreen(
    onBackClick: () -> Unit,
    viewModel: ProfileViewModel
) {
    viewModel.getName()
    viewModel.getAge()
    viewModel.getCategory()

    //region BARRA SUPERIOR CON NOMBRE Y FLECHA PARA IR ATRÁS
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        text = "Perfil de ${viewModel.profileName.value}",
                        fontFamily = abrilFatface
                    )

                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    )
    //endregion
    {
        //PROFILE CONTENT
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_dogshape),
                        contentDescription = "profilePhoto",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(150.dp)
                            .border(1.dp, Color.Yellow, CircleShape)
                    )
                }
                //region NOMBRE Y EDAD
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Nombre de la mascota
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "${viewModel.profileName.value}".uppercase(),
                        fontSize = 20.sp,
                        fontFamily = abrilFatface,
                    )

                    Spacer(Modifier.height(16.dp))
                    // Edad de la mascota
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "${viewModel.profileAge.value} años",
                        fontSize = 20.sp,
                        fontFamily = abrilFatface,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(Modifier.height(16.dp))

                    //categoría
                    Card(
                        modifier = Modifier.padding(8.dp),
                        elevation = 10.dp
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "${viewModel.profileCategory.value}",
                            fontSize = 16.sp,
                            fontFamily = abrilFatface
                        )
                    }
                }
                //endregion
            }
            //endregion
        }
    }
}