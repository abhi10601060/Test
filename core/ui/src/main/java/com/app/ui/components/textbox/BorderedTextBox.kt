package com.app.ui.components.textbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BorderedTextBox(
    modifier: Modifier = Modifier,
    value: String ,
    onValueChange: (String) -> Unit,
    fontColor: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 24.sp,
    backgroundColor: Color = Color.Black,
    borderColor: Color = Color.White,
    borderThickNess: Dp = 1.dp,
    cornerRadius: Dp = 10.dp,
    readonly: Boolean = false,
    maxLines: Int = 1,
    placeholder: String = "",
) {
    BasicTextField(
        modifier = modifier,
        value =  value,
        onValueChange = onValueChange,
        readOnly = readonly,
        maxLines = maxLines,
        textStyle = TextStyle(
            color = fontColor,
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        decorationBox = { innerTextField ->
            Box(modifier = modifier
                .border(width = borderThickNess, color = borderColor, shape = RoundedCornerShape(cornerRadius))
                .background(shape = RoundedCornerShape(cornerRadius), color = backgroundColor)
                .padding(Dp(fontSize.value / 3))
            ){
                if (placeholder.isNotEmpty() && value.isEmpty()) Text(modifier = Modifier.alpha(0.5f) ,text = placeholder, color = Color.LightGray, fontSize = fontSize)
                innerTextField()
            }
        }
    )
}


@Preview
@Composable
private fun BorderedTextBoxPrev() {
    var value by remember { mutableStateOf("") }
    BorderedTextBox(
        value = value,
        onValueChange = {value = it},
        placeholder = "Test"
    )
}