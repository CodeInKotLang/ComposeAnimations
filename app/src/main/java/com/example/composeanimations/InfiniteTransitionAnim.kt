package com.example.composeanimations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteTransitionAnim() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000), RepeatMode.Reverse),
        label = ""
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        )
    }
}

/*
MainActivity Code
          var isVisible by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (isVisible) {
                        PulseLoadingAnim(
                            modifier = Modifier.align(Alignment.Center),
                            icon = Icons.Default.Call
                        )
                    }
                    Button(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onClick = { isVisible = !isVisible }
                    ) {
                        Text(text = "Click Here")
                    }
                }
 */

@Composable
fun PulseLoadingAnim(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val size by infiniteTransition.animateFloat(
        initialValue = 50f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000, easing = LinearEasing)),
        label = ""
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000, easing = LinearEasing)),
        label = ""
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .alpha(alpha)
                .background(color = MaterialTheme.colorScheme.surfaceVariant, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}