package fr.ailurus.housepassp2p

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Constants {
    const val CODE_CHAR_COUNT = 6
}

object AppDimensions {
    val PaddingSmall = 8.dp
    val PaddingMedium = 16.dp
    val PaddingLarge = 24.dp
    val PaddingExtraLarge = 32.dp

    val AuthFieldWidth = 256.dp
    val ElevationMedium = 16.dp
}

object BordersConfig {
    val ShapeSmall = RoundedCornerShape(8.dp)
    val ShapeMedium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    val ShapeLarge = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)

    val ShapeMediumUpperAngle = RoundedCornerShape(
        topStart = 12.dp,
        topEnd = 12.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
}

object PinConfig {
    val LetterSpacing = 8.sp
    val FontSize = 24.sp
}