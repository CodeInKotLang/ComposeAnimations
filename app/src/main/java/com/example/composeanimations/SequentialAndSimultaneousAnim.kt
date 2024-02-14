package com.example.composeanimations

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Sequential() {
    val yOffset = remember { Animatable(initialValue = 0f) }
    val scale = remember { Animatable(initialValue = 1f) }

    var triggerAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = triggerAnimation) {
        if (triggerAnimation) {
            coroutineScope {
                yOffset.animateTo(targetValue = 1000f)
            }
            coroutineScope {
                scale.animateTo(targetValue = 3f)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    translationY = yOffset.value
                }
                .align(Alignment.TopCenter)
                .size(100.dp)
                .background(Color.Red)
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { triggerAnimation = true }
        ) {
            Text(text = "Start")
        }
    }
}

@Composable
fun Simultaneous() {
    val yOffset = remember { Animatable(initialValue = 0f) }
    val scale = remember { Animatable(initialValue = 1f) }

    var triggerAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = triggerAnimation) {
        if (triggerAnimation) {
            launch {
                yOffset.animateTo(targetValue = 1000f)
            }
            launch {
                scale.animateTo(targetValue = 3f)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    translationY = yOffset.value
                }
                .align(Alignment.TopCenter)
                .size(100.dp)
                .background(Color.Red)
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { triggerAnimation = true }
        ) {
            Text(text = "Start")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePostLikeAnim() {
    val colorAlpha = remember { Animatable(initialValue = 0f) }
    val scale = remember { Animatable(initialValue = 0f) }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {},
                    onDoubleClick = {
                        scope.launch {
                            coroutineScope {//Enter Animation
                                launch {//Fade in
                                    colorAlpha.animateTo(targetValue = 1f)
                                }
                                launch {//Scale Up
                                    scale.animateTo(targetValue = 2f)
                                }
                            }
                            delay(500)
                            coroutineScope {//Exit Animation
                                launch {//fade out
                                    colorAlpha.animateTo(targetValue = 0f)
                                }
                                launch {//scale up
                                    scale.animateTo(targetValue = 3f)
                                }
                            }
                            coroutineScope {
                                scale.animateTo(targetValue = 0f)
                            }
                        }
                    }
                ),
            painter = painterResource(id = R.drawable.img_snake),
            contentDescription = ""
        )
        Icon(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    alpha = colorAlpha.value
                }
                .size(50.dp),
            imageVector = Icons.Default.Favorite,
            contentDescription = "",
            tint = Color.White
        )
    }
}






