package com.app.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.buttons.RegularImageButton
import com.app.ui.components.textbox.BorderedTextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputDialog(
    title: String,
    value: String,
    placeHolder: String = "",
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
    onCloseClick: () -> Unit,
    onYesClick: () -> Unit
) {
    BasicAlertDialog(
        modifier = Modifier.padding(10.dp),
        onDismissRequest = {}
    ) {
        Column(
            modifier = Modifier.wrapContentSize().background(shape = RoundedCornerShape(20.dp), color = Color.DarkGray).padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Divider(modifier = Modifier.padding(20.dp).fillMaxWidth())

            BorderedTextBox(
                modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth().height(Dp((maxLines * 40).toFloat()) ),
                value = value,
                onValueChange = onValueChange,
                backgroundColor = Color.Black.copy(0.3f),
                maxLines = maxLines,
                placeholder = placeHolder
            )

            Row(
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RegularImageButton(icon = Icons.Default.Close, onClick = onCloseClick, tint = Color.Red)
                RegularImageButton(icon = Icons.Default.Done, onClick = onYesClick, tint = Color.Green)
            }
        }
    }
}

@Preview
@Composable
private fun TextInputDialogPrev() {

    var text by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()){
        TextInputDialog(
            title = "Test Input",
            value = text,
            placeHolder = "Enter Text",
            onValueChange = {text = it},
            onYesClick = {},
            onCloseClick = {},
            maxLines = 2
        )
    }

}