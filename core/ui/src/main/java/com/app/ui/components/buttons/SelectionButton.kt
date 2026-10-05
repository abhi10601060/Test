package com.app.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun <T> SelectionButton(
    modifier: Modifier = Modifier,
    item: T,
    itemString: (T) -> String = { it.toString() },
    fontSize: TextUnit = 24.sp,
    selectionColor: Color = Color.LightGray,
    unselectedColor: Color = Color.Gray,
    fontColor: Color = Color.White,
    isSelected: Boolean = false,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier
            .background(
                color = if (isSelected) selectionColor else unselectedColor,
                shape = RoundedCornerShape(Dp(fontSize.value / 8))
            )
            .padding(Dp(fontSize.value / 4)),
        text = itemString(item),
        textAlign = TextAlign.Center,
        color = fontColor,
        fontSize = fontSize,
        maxLines = maxLines
    )
}


@Preview
@Composable
private fun SelectionButtonPrev() {
    SelectionButton(item = "Select it")
}