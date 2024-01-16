package com.example.composeanimations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedContentAnim() {

    var displayNumber by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            onClick = { if (displayNumber != 0) displayNumber-- },
            enabled = displayNumber != 0
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = ""
            )
        }
        AnimatedContent(
            targetState = displayNumber,
            label = "",
            transitionSpec = {
                if (targetState > initialState) {
                    //if target number is larger, it slides up and fades in.
                    ContentTransform(
                        targetContentEnter = slideInVertically { h -> h } + fadeIn(),
                        initialContentExit = slideOutVertically { h -> -h } + fadeOut()
                    )
                } else {
                    //if target number is smaller, it slides down and fades in.
                    ContentTransform(
                        targetContentEnter = slideInVertically { h -> -h } + fadeIn(),
                        initialContentExit = slideOutVertically { h -> h } + fadeOut()
                    )
                }
            }
        ) { displayNumber ->
            Text(
                text = "$displayNumber",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(10.dp)
            )
        }
        FilledIconButton(
            onClick = { displayNumber++ }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = ""
            )
        }
    }
}