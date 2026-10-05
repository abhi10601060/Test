package com.app.ui.components.radio_buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ui.components.buttons.SelectionButton

@Composable
fun <T> HorizontalScrollableRadioButtons(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T?,
    itemsString: (T) -> String = { it.toString() },
    onItemSelected: (index: Int, item: T) -> Unit,
) {
    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(items = items) { idx, item ->
            SelectionButton(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onItemSelected(idx, item)
                    }, item = item,
                itemString = itemsString,
                isSelected = selectedItem == item
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