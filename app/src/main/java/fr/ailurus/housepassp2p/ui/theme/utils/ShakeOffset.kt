package fr.ailurus.housepassp2p.ui.theme.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes

suspend fun Animatable<Float, *>.shake(intensity: Float = 15f) {
    this.animateTo(
        targetValue = 0f,
        animationSpec = keyframes {
            durationMillis = 400
            (-intensity) at 50
            (intensity) at 150
            (-intensity) at 250
            (intensity) at 350
            0f at 400
        }
    )
}