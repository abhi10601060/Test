package com.app.ui.components.textbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.extension.dashedBottomBorder


@Composable
fun DashUnderlineTextBox(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    fontColor: Color = Color.Black,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onValueChange: (value : String) -> Unit,
    backgroundColor: Color = Color.White,
    dashColor: Color = Color.Black,
    dashThickness: Dp = 1.dp,
    dashLineWidth: Dp = 4.dp,
    maxLines: Int = 1,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = fontColor
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .background(color = backgroundColor)
                    .dashedBottomBorder(
                        color = dashColor,
                        thickness = dashThickness,
                        lineWidth = dashLineWidth
                    ).padding(bottom = Dp(fontSize.value / 8), start = dashLineWidth)
            ) {
                if (value.isEmpty()) Text(text = placeholder, fontSize = fontSize, color = Color.Gray)
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun DashUnderlineTextBoxPrev() {
    var text by remember { mutableStateOf("") }
    DashUnderlineTextBox(
        modifier = Modifier.width(80.dp),
        value = text,
        placeholder = "test",
        onValueChange = {
            text = it
        }
    )
}
