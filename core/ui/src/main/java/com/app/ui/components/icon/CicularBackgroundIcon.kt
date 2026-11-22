package com.app.ui.components.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularBackgroundIcon(
    modifier: Modifier = Modifier,
    size: Dp,
    imageVector: ImageVector? = null,
    contentDescription: String? = null,
    bgColor: Color = Color.White,
    iconTint: Color = Color.Black
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color = bgColor, shape = CircleShape)
            .padding(size * 0.18f),
        contentAlignment = Alignment.Center
    ) {
        if (imageVector != null){
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                tint = iconTint
            )
        }
    }
}

@Preview
@Composable
private fun CircularBackgroundIconPreview() {
    CircularBackgroundIcon(
        size = 80.dp,
        imageVector = Icons.Default.QrCodeScanner,
        bgColor = Color.Blue,
        iconTint = Color.White
    )
}
