package com.example.tinpet

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import  com.example.tinpet.graphs.NavGraph
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")


@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val screens = listOf(
        AppScreens.Places,
        AppScreens.Connect,
        AppScreens.Home,
        AppScreens.ChatUsers,
        AppScreens.Profile,
        AppScreens.Settings,
        AppScreens.Requests,
        AppScreens.AboutUs,
        AppScreens.Notifications,
        AppScreens.Friends,
        AppScreens.Pets
    )
    val showTopBar = navController.currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }
    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(onClick = {
                    navController.navigate(AppScreens.Home.route)
                } )
            }
        },
        bottomBar = { BottomBar2(navController = navController) }
    ) {
        NavGraph(navController = navController)
    }
}

//region MENÚ SUPERIOR
@Composable
fun TopBar(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primaryVariant),
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
            modifier = Modifier
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
fun BottomBar2(navController: NavHostController) {
    val screens = listOf(
        AppScreens.Places,
        AppScreens.Connect,
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
                    currentDestination = currentDestination,
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
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        /*label = {
            Text(text = screen.title)
        },*/
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Color.Black
                }
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
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