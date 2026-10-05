package com.app.ui.components.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.textbox.BorderedTextBox

/**
 * A generic bordered text dropdown composable with hoisted state for [selectedItem].
 *
 * @param T The type of item in the dropdown list.
 * @param items List of items to show in the dropdown.
 * @param selectedItem Currently selected item passed from the caller (hoisted state).
 * @param onItemSelected Callback triggered when an item is selected, supplying its index and selected item [T].
 * @param itemToString Lambda to extract display string representation from each item [T]. Defaults to `{ it.toString() }`.
 * @param placeholder Text displayed when no item is selected.
 * @param fontColor Text color for the text box and dropdown items.
 * @param fontSize Font size for the text box and dropdown items.
 * @param fontWeight Font weight for the text box and dropdown items.
 * @param backgroundColor Background color for the text box.
 * @param borderColor Border color for the text box.
 * @param borderThickness Thickness of the border.
 * @param cornerRadius Corner radius for the rounded border.
 * @param dropdownBackgroundColor Background color for the popup menu.
 * @param dropdownBorderColor Border color for the popup menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BorderedTextDropdown(
    modifier: Modifier = Modifier.widthIn(40.dp),
    items: List<T>,
    selectedItem: T? = null,
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> },
    itemToString: (T) -> String = { it.toString() },
    placeholder: String = "Select Item",
    enabled: Boolean = true,
    fontColor: Color = Color.White,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    backgroundColor: Color = Color.Black,
    borderColor: Color = Color.White,
    borderThickness: Dp = 1.dp,
    cornerRadius: Dp = 5.dp,
    dropdownBackgroundColor: Color = backgroundColor,
    dropdownBorderColor: Color = borderColor
) {
    var isExpanded by remember { mutableStateOf(false) }

    val displayValue = selectedItem?.let(itemToString) ?: placeholder

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { if (enabled) isExpanded = !isExpanded }
    ) {
        BorderedTextBox(
            modifier = modifier.clickable {
                if (enabled) isExpanded = !isExpanded
            },
            value = displayValue,
            onValueChange = {},
            fontColor = fontColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            backgroundColor = backgroundColor,
            borderColor = borderColor,
            borderThickNess = borderThickness,
            cornerRadius = cornerRadius,
            readonly = true
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .background(color = dropdownBackgroundColor)
                .border(
                    width = borderThickness,
                    color = dropdownBorderColor,
                    shape = RoundedCornerShape(cornerRadius)
                )
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = itemToString(item),
                            color = fontColor,
                            fontSize = fontSize,
                            fontWeight = fontWeight
                        )
                    },
                    onClick = {
                        onItemSelected(index, item)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun BorderedTextDropdownStringPreview() {
    val items = listOf("Option 1", "Option 2", "Option 3")
    var selectedItem by remember { mutableStateOf<String?>(null) }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    BorderedTextDropdown(
        items = items,
        selectedItem = selectedItem,
        onItemSelected = { index, item ->
            selectedIndex = index
            selectedItem = item
        }
    )
}

private data class User(val id: Int, val name: String)

@Preview
@Composable
private fun BorderedTextDropdownCustomObjectPreview() {
    val users = listOf(
        User(1, "a"),
        User(2, "v"),
        User(3, "c")
    )
    var selectedUser by remember { mutableStateOf<User?>(null) }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        BorderedTextDropdown(
            modifier = Modifier.align(Alignment.Center),
            items = users,
            selectedItem = selectedUser,
            onItemSelected = { index, user ->
                selectedIndex = index
                selectedUser = user
            },
            itemToString = { it.name },
            fontColor = Color.Yellow,
            borderColor = Color.Cyan,
            borderThickness = 2.dp
        )
    }
}
