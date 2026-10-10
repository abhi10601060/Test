package com.app.ui.components.radio_buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.buttons.SelectionButton
@Composable
fun <T> HorizontalScrollableRadioButtons(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T?,
    itemsString: (T) -> String = { it.toString() },
    onItemSelected: (index: Int, item: T) -> Unit,
    fontSize: TextUnit = 24.sp,
    maxLines: Int = 1,
) {
    // Reset when anything affecting size changes, otherwise the max never shrinks
    var maxHeightPx by remember(items, fontSize, maxLines) { mutableIntStateOf(0) }
    val maxHeight = with(LocalDensity.current) { maxHeightPx.toDp() }

    LazyRow(modifier = modifier) {
        itemsIndexed(items = items) { idx, item ->
            SelectionButton(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .heightIn(min = maxHeight)              // 1. apply the shared height
                    .onSizeChanged { size ->                // 2. report own height
                        if (size.height > maxHeightPx) maxHeightPx = size.height
                    }
                    .clickable { onItemSelected(idx, item) },
                item = item,
                itemString = itemsString,
                isSelected = selectedItem == item,
                fontSize = fontSize,
                maxLines = maxLines
            )
        }
    }
}

data class Test(val name: String)

@Preview
@Composable
private fun HorizontalScrollableRadioButtonsPrev() {
    val list =
        listOf(Test("Abhi"), Test("Himanshu"), Test("Shubham"), Test("Nazim"), Test("Aditya"))
    var selectedItem by remember { mutableStateOf<Test?>(null) }
    HorizontalScrollableRadioButtons(
        modifier = Modifier.fillMaxWidth(),
        items = list,
        itemsString = { item -> item.name },
        selectedItem = selectedItem,
        onItemSelected = { _, item ->
            selectedItem = item
        }
    )
}