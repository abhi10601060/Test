package com.app.ui.components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ui.components.buttons.SelectionButton
import com.app.ui.components.radio_buttons.Test


@Composable
fun <T> HorizontalScrollableCheckBox(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedItems: List<T>,
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
                isSelected = selectedItems.contains(item)
            )
        }
    }
}


@Preview
@Composable
private fun HorizontalScrollableCheckBoxPrev() {
    val list = listOf(Test("Abhi"), Test("Himanshu"), Test("Shubham"), Test("Nazim"), Test("Aditya"))
    val selectedItems = remember { mutableStateListOf<Test>() }
    HorizontalScrollableCheckBox(
        modifier = Modifier.fillMaxWidth(),
        items = list,
        itemsString = { item -> item.name },
        selectedItems = selectedItems,
        onItemSelected = { _, item ->
            if (selectedItems.contains(item)) selectedItems.remove(item)
            else selectedItems.add(item)
        }
    )
}