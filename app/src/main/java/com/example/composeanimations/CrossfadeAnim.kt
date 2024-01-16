package com.example.composeanimations

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun CrossfadeAnim() {

    var cardPosition by remember { mutableStateOf(CreditCardPosition.FRONT) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = cardPosition,
            label = ""
        ) {
            when(it) {
                CreditCardPosition.FRONT -> {
                    CreditCard(imageResId = R.drawable.card_front)
                }
                CreditCardPosition.BACK -> {
                    CreditCard(imageResId = R.drawable.card_back)
                }
            }
        }
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                cardPosition = when(cardPosition) {
                    CreditCardPosition.FRONT -> CreditCardPosition.BACK
                    CreditCardPosition.BACK -> CreditCardPosition.FRONT
                }
            }
        ) {
            Text(text = "Turn Card")
        }
    }
}

@Composable
fun CreditCard(
    modifier: Modifier = Modifier,
    imageResId: Int
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = imageResId),
        contentDescription = ""
    )
}

enum class CreditCardPosition {
    FRONT,
    BACK
}