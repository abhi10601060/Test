package com.app.ui.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularTextIcon(
    modifier: Modifier = Modifier,
    size: Dp,
    text: String,
    bgColor: Color = Color.White,
    fontColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Bold
) {
    BoxWithConstraints(
        modifier = modifier
            .size(size)
            .background(color = bgColor, shape = CircleShape)
            .padding(size * 0.14f),
        contentAlignment = Alignment.Center
    ) {
        val density = LocalDensity.current
        val textMeasurer = rememberTextMeasurer()

        val maxAvailableWidthPx = with(density) { constraints.maxWidth.toFloat() }
        val maxAvailableHeightPx = with(density) { constraints.maxHeight.toFloat() }

        val fontSizeSp = remember(text, maxAvailableWidthPx, maxAvailableHeightPx, fontWeight) {
            var currentSp = (maxWidth.value * 0.6f)
            while (currentSp > 6f) {
                val style = TextStyle(
                    fontSize = currentSp.sp,
                    fontWeight = fontWeight
                )
                val layoutResult = textMeasurer.measure(
                    text = AnnotatedString(text),
                    style = style,
                    maxLines = 1
                )
                if (layoutResult.size.width <= maxAvailableWidthPx && layoutResult.size.height <= maxAvailableHeightPx) {
                    break
                }
                currentSp -= 0.5f
            }
            currentSp.sp
        }

        Text(
            text = text,
            color = fontColor,
            fontSize = fontSizeSp,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
    }
}

@Preview
@Composable
private fun CircularTextIconPreview() {
    CircularTextIcon(
        size = 80.dp,
        text = "QR",
        bgColor = Color.Blue,
        fontColor = Color.White
    )
}
