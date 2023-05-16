package com.example.tinpet

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tinpet.graphs.Graph
import  com.example.tinpet.graphs.NavGraph
import com.example.tinpet.screens.mainMenu.profile.ProfileScreen
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val screens = listOf(
        AppScreens.Places,
        //AppScreens.Connect,
        AppScreens.Home,
        AppScreens.ChatUsers,
        AppScreens.Settings,
        AppScreens.Requests,
        AppScreens.AboutUs,
        AppScreens.Notifications,
        AppScreens.Friends,
        AppScreens.Pets
    )
    val profileScreens = listOf(AppScreens.Profile)
    val profileTopBar = navController.currentBackStackEntryAsState().value?.destination?.route in profileScreens.map { it.route }
    val showTopBar = navController.currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }
    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(onClick = {
                    navController.navigate(AppScreens.Home.route)
                } )
            }else if(profileTopBar){
                ProfileTopBar(
                    onClick = {
                        navController.navigate(AppScreens.Home.route)
                    },
                    onSetClick = {
                        navController.navigate(AppScreens.Settings.route)
                    }
                )
            }
        },
        bottomBar = {
            BottomBar2(navController = navController)
        }
    ) {
        NavGraph(navController = navController)
    }
}

//region MENÚ SUPERIOR PERFIL
@Composable
fun ProfileTopBar(
    onClick: () -> Unit,
    onSetClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .align(Alignment.Center),
            painter = if (isSystemInDarkTheme()) {
                painterResource(R.drawable.icon_pawprint_black)
            } else {
                painterResource(R.drawable.icon_pawprint_white)
            },
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .clickable { onClick() }
                .align(Alignment.Center),
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
        // Botón de Ajustes
        Button(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = { onSetClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Icon(
                Icons.Filled.Settings,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null
            )
        }
    }
}
//endregion

//region MENÚ SUPERIOR
@Composable
fun TopBar(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center)
                .padding(8.dp),
            painter = if (isSystemInDarkTheme()) {
                painterResource(R.drawable.icon_pawprint_black)
            } else {
                painterResource(R.drawable.icon_pawprint_white)
            },
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { onClick() },
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
    }

}
//endregion

//region MENÚ INFERIOR
@Composable
fun BottomBar2(
    navController: NavHostController,
) {
    val screens = listOf(
        AppScreens.Places,
        //AppScreens.Connect,
        AppScreens.Home,
        AppScreens.ChatUsers,
        AppScreens.Profile
    )

    val navBackStackEntry by navController
        .currentBackStackEntryAsState()
    val currentDestination =
        navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    navController = navController
                )
            }
        }
    }
}
//endregion

//region AÑADIR ITEM
@Composable
fun RowScope.AddItem(
    screen: AppScreens,
    isSelected: Boolean,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (isSelected) {
                    MaterialTheme.colors.onError // Cambia el color del icono cuando está seleccionado
                } else {
                    if (isSystemInDarkTheme()) {
                        Color.White
                    } else {
                        Color.Black
                    }
                }
            )
        },
        selected = isSelected,
        /*currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,*/
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
//endregion