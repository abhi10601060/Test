package com.app.ui.components.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Extension function on [Modifier] to draw a dashed bottom border.
 *
 * @param color Color of the dashed line.
 * @param thickness Stroke thickness of the line.
 * @param lineWidth Length of each dash line segment.
 * @param gapWidth Length of the gap between dash segments.
 */
fun Modifier.dashedBottomBorder(
    color: Color = Color.Black,
    thickness: Dp = 1.dp,
    lineWidth: Dp = 4.dp,
    gapWidth: Dp = lineWidth,
): Modifier = drawBehind {
    val strokeWidthPx = thickness.toPx()
    val dashWidthPx = lineWidth.toPx()
    val gapWidthPx = gapWidth.toPx()
    val y = size.height - strokeWidthPx / 2f

    drawLine(
        color = color,
        start = Offset(x = 0f, y = y),
        end = Offset(x = size.width, y = y),
        strokeWidth = strokeWidthPx,
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashWidthPx, gapWidthPx),
            phase = 0f
        )
    )
}

/**
 * Alias for [dashedBottomBorder] for convenience.
 */
fun Modifier.dashBottomBorder(
    color: Color = Color.Black,
    thickness: Dp = 1.dp,
    lineWidth: Dp = 4.dp,
    gapWidth: Dp = lineWidth,
): Modifier = dashedBottomBorder(color, thickness, lineWidth, gapWidth)
