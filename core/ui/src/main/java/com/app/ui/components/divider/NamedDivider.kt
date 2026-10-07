package com.app.ui.components.divider

import androidx.annotation.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NamedDivider(
    modifier: Modifier = Modifier,
    text: String,
    thickness: Dp = 1.dp,
    fontSize: TextUnit = 15.sp,
    color: Color = Color.Black
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier.height(thickness)
                .background(color = color)
                .weight(1f)
        )

        Text(modifier = Modifier.padding(horizontal = fontSize.value.dp), text =  text, fontSize = fontSize, color = color)

        Spacer(
            modifier = Modifier.height(thickness)
                .background(color = color)
                .weight(1f)
        )
    }
}