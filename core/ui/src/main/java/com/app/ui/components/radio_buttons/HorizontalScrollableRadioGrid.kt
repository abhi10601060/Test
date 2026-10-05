package com.app.ui.components.radio_buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
fun <T> HorizontalScrollableRadioGrid(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemsString: (T) -> String,
    selectedItem: T? = null,
    rows: Int = 2,
    columns: Int = 3,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> },
) {
    val totalItemsOnSinglePage = rows * columns
    val pageCount = (items.size + totalItemsOnSinglePage - 1) / totalItemsOnSinglePage
    val state = rememberPagerState(initialPage = 0) { pageCount }

    HorizontalPager(
        modifier = modifier,
        state = state
    ) { page ->
        val startIndex = page * totalItemsOnSinglePage
        val endIndex = minOf(startIndex + totalItemsOnSinglePage, items.size)
        val pageItems = items.subList(startIndex, endIndex)

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(columns),
            userScrollEnabled = false,
            verticalArrangement = verticalArrangement
        ) {
            itemsIndexed(items = pageItems) { idx, item ->
                SelectionButton(
                    modifier = Modifier
                        .clickable {
                            onItemSelected(startIndex + idx, item)
                        }
                        .padding(5.dp),
                    item = item,
                    itemString = itemsString,
                    isSelected = item == selectedItem
                )
            }
        }
    }
}

@Preview
@Composable
private fun HorizontalScrollableRadioGridPrev() {
    val list = listOf(
        Test("Abhi"), Test("Himanshu"), Test("Shubham"),
        Test("Nazim"), Test("Aditya"), Test("Amit"),
        Test("Yash"), Test("Jay")
    )
    var selectedItem by remember { mutableStateOf<Test?>(null) }

    HorizontalScrollableRadioGrid(
        modifier = Modifier.fillMaxWidth(),
        items = list,
        itemsString = { item -> item.name },
        selectedItem = selectedItem,
        onItemSelected = { _, item ->
            selectedItem = item
        }
    )
}
