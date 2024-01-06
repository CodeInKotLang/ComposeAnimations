package com.example.composeanimations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
fun AnimatedVisibilityAnim() {
    var isVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
            )
        }
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { isVisible = !isVisible }
        ) {
            Text(text = "Start")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoreOptionBottomBar() {
    var isVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_snake),
            contentDescription = "",
            modifier = Modifier.combinedClickable(
                onClick = { /* Open the Image */ },
                onLongClick = { isVisible = true }
            )
        )
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = isVisible,
            enter = slideInVertically(tween(durationMillis = 300)) { h -> h },
            exit = slideOutVertically(tween(durationMillis = 300)) { h -> h }
        ) {
            BottomBar(onCloseClick = { isVisible = false })
        }
    }
}

@Composable
private fun BottomBar(
    onCloseClick: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = onCloseClick,
            icon = {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
        )
    }
}





