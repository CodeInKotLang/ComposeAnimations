package com.example.composeanimations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

/*
********** MainActivityCode ************
var hasFinished by remember { mutableStateOf(false) }
Box(
modifier = Modifier.fillMaxSize()
) {
    if (hasFinished) {
        CongratsLottieAnim(
            modifier = Modifier.align(Alignment.Center),
        )
    }
    Button(
        modifier = Modifier.align(Alignment.BottomCenter),
        onClick = { hasFinished = !hasFinished }
    ) {
        Text(
            text = if (hasFinished) "Next" else "Submit"
        )
    }
}


var isPlaying by remember { mutableStateOf(false) }
Box(
modifier = Modifier.fillMaxSize()
) {
    SendingLottieAnim(
        modifier = Modifier.align(Alignment.Center),
        isPlaying = isPlaying
    )
    Button(
        modifier = Modifier.align(Alignment.BottomCenter),
        onClick = { isPlaying = !isPlaying }
    ) {
        Text(text = if (isPlaying) "Stop" else "Play")
    }
}
}
*/


@Composable
fun CongratsLottieAnim(
    modifier: Modifier = Modifier
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.congratutaion_lottie)
    )

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition
    )
}

@Composable
fun SendingLottieAnim(
    modifier: Modifier = Modifier,
    isPlaying: Boolean
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.sending_lottie)
    )

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f,
        isPlaying = isPlaying
    )
}