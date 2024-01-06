package com.example.composeanimations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AnimateAsState() {
    var animateRotation by remember { mutableStateOf(false) }
    val rotationDegrees by animateFloatAsState(
        targetValue = if (animateRotation) 45f else 0f,
        label = ""
    )
    val color by animateColorAsState(
        targetValue =if (animateRotation) Color.Blue else Color.Red,
        label = ""
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
//                .rotate(rotationDegrees)
                .background(color)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { animateRotation = !animateRotation }
                )
        )
    }
}

@Composable
fun AnimateIntAsState() {
    var animateText by remember { mutableStateOf(false) }
    val textValue by animateIntAsState(
        targetValue = if (animateText) 50 else 0,
        label = "",
        animationSpec = tween(durationMillis = 1000)
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$textValue",
            style = MaterialTheme.typography.displayLarge
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { animateText = !animateText }
        ) {
            Text(text = "Start")
        }
    }
}

@Composable
fun AnimateCar() {
    var animateCar by remember { mutableStateOf(false) }
    val xAxisOffset by animateDpAsState(
        targetValue = if (animateCar) 300.dp else 0.dp,
        label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessHigh
        )
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .height(50.dp)
                .align(Alignment.CenterStart)
                .offset(xAxisOffset),
            painter = painterResource(R.drawable.img_car),
            contentDescription = ""
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { animateCar = !animateCar }
        ) {
            Text(text = "Start")
        }
    }
}