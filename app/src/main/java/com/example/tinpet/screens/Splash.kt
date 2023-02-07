package com.example.tinpet.screens

import android.window.SplashScreen
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tinpet.R
import com.example.tinpet.graphs.Graph
import com.example.tinpet.ui.theme.abrilFatface
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController:NavHostController) {
    LaunchedEffect(key1 = true){
        delay(3000)
        navController.popBackStack()
        navController.navigate(Graph.AUTHENTICATION)
    }
    Splash()
}
enum class LogoPosition{
    Start,
    Finish
}
@Composable
fun Splash() {
    val infiniteTransition = rememberInfiniteTransition()
    val logoSize by infiniteTransition.animateFloat(
        initialValue = 100.0f,
        targetValue = 250.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, delayMillis = 100, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image(
           modifier = Modifier
               .size(logoSize.dp)
               .padding(8.dp),
           painter = painterResource(R.drawable.icon_pawprint)
           /*painter = if (isSystemInDarkTheme()) {
               painterResource(R.drawable.icon_pawprint_black)
           } else {
               painterResource(R.drawable.icon_pawprint_white)
           }*/,
           contentDescription = null,
           )
        Text(
            style = TextStyle(
                shadow = Shadow(
                    color = Color.DarkGray,
                    offset = Offset(2.0f, 5.0f),
                    blurRadius = 2f
                )
            ),
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}