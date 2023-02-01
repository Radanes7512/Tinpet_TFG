package com.example.tinpet.homeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.tinpet.R
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardStack(
    modifier: Modifier = Modifier,
    items: MutableList<Item>,
    thresholdConfig: (Float, Float) -> ThresholdConfig = { _, _ -> FractionalThreshold(0.2f) },
    velocityThreshold: Dp = 125.dp,
    onSwipeLeft: (item: Item) -> Unit = {},
    onSwipeRight: (item: Item) -> Unit = {},
    onEmptyStack: (lastItem: Item) -> Unit = {}
) {
    var i by remember {
        mutableStateOf(items.size - 1)
    }

    if (i == -1) {
        onEmptyStack(items.last())
    }

    val cardStackController = rememberCardStackController()

    cardStackController.onSwipeLeft = {
        onSwipeLeft(items[i])
        i--
    }

    cardStackController.onSwipeRight = {
        onSwipeRight(items[i])
        i--
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        val stack = createRef()

        Box(modifier = modifier
            .constrainAs(stack) {
                top.linkTo(parent.top)
            }
            .draggableStack(
                controller = cardStackController,
                thresholdConfig = thresholdConfig,
                velocityThreshold = velocityThreshold
            )
            .fillMaxHeight()) {
            items.asReversed().forEachIndexed { index, item ->
                Card(
                    modifier = Modifier
                        .moveTo(
                            x = if (index == i) cardStackController.offsetX.value else 0f,
                            y = if (index == i) cardStackController.offsetY.value else 0f
                        )
                        .visible(visible = index == i || index == i - 1)
                        .graphicsLayer(
                            rotationZ = if (index == i) cardStackController.rotation.value else 0f,
                            scaleX = if (index < i) cardStackController.scale.value else 1f,
                            scaleY = if (index < i) cardStackController.scale.value else 1f
                        ), item, cardStackController
                )
            }
        }
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier, item: Item, cardStackController: CardStackController
) {
    Column {
        if (item.painter != null) {
            AsyncImage(
                model = item.painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(
                text = item.text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(
                text = item.subText,
                color = Color.White,
                fontSize = 20.sp
            )
        }
        // CATEGORIAS
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), elevation = 10.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(elevation = 10.dp) {
                    Text(modifier = Modifier.padding(5.dp), text = "Categoría 1")
                }
                Card(elevation = 10.dp) {
                    Text(modifier = Modifier.padding(5.dp), text = "Categoría 2")
                }
                Card(elevation = 10.dp) {
                    Text(modifier = Modifier.padding(5.dp), text = "Categoría 3")
                }
                Card(modifier = Modifier.size(25.dp), elevation = 10.dp) {
                    Icon(modifier = Modifier
                        .clickable { }
                        .padding(5.dp),
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = null)
                }
            }
        }
        // ICONOS DISLIKE Y LIKE
        val ic_size = 70.dp
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            Image(
                modifier = Modifier
                    .clickable { cardStackController.swipeLeft() }
                    .size(ic_size, ic_size),
                painter = painterResource(R.drawable.icon_notlike),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .clickable { cardStackController.swipeRight() }
                    .size(ic_size, ic_size),
                painter = painterResource(R.drawable.icon_like),
                contentDescription = null,
            )
        }
    }
    }





data class Item(
    val painter: Int?, val text: String = "", val subText: String = ""
)

fun Modifier.moveTo(
    x: Float, y: Float
) = this.then(Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x.roundToInt(), y.roundToInt())
    }
})

fun Modifier.visible(
    visible: Boolean = true
) = this.then(Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    if (visible) {
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    } else {
        layout(0, 0) {}
    }
})