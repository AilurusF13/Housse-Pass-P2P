package fr.ailurus.housepassp2p.ui.theme.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PinVisualTransformation(
    val mask: Char = '●',
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val out = mask.toString().repeat(text.text.length)
        return TransformedText(
            androidx.compose.ui.text
                .AnnotatedString(out),
            OffsetMapping.Companion.Identity,
        )
    }
}
