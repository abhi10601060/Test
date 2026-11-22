package com.app.ui.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.icon.CircularBackgroundIcon
import com.app.ui.components.text.CircularTextIcon

@Composable
fun PartList(
    modifier: Modifier = Modifier,
    partList: List<Part> = listOf(),
    selectedPart: Part? = null
) {
    Column(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(modifier = Modifier.weight(0.64f),
                text = "Part Name", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Box(modifier = Modifier.weight(0.18f), contentAlignment = Alignment.Center){
                CircularTextIcon(size = 40.dp, text = "QR")
            }

            Box(modifier = Modifier.weight(0.18f), contentAlignment = Alignment.Center){
                CircularTextIcon(size = 40.dp, text = "Result")
            }
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.DarkGray.copy(alpha = 0.6f))
        ) {
            itemsIndexed(items = partList){ idx, part ->
                PartListItem(part = part, isSelected = part == selectedPart)
            }
        }

    }
}

private data class CircularIconData(
    val bgColor: Color,
    val imageVector: ImageVector? = null,
    val iconColor: Color = Color.White
)

private val Part.qrIconData: CircularIconData
    get() = when {
        isBarcodeScanned -> CircularIconData(
            bgColor = Color.Green.copy(alpha = 0.9f),
            imageVector = Icons.Default.Done
        )
        isBarcodeRequired -> CircularIconData(
            bgColor = Color.DarkGray.copy(alpha = 0.6f)
        )
        else -> CircularIconData(
            bgColor = Color.White.copy(alpha = 0.7f),
            imageVector = Icons.Default.Remove,
            iconColor = Color.Black
        )
    }

private val Part.resultIconData: CircularIconData
    get() = when (status?.statusCode) {
        null -> CircularIconData(bgColor = Color.DarkGray.copy(alpha = 0.6f))
        "2000" -> CircularIconData(
            bgColor = Color.Green.copy(alpha = 0.9f),
            imageVector = Icons.Default.Done
        )
        else -> CircularIconData(
            bgColor = Color.Red.copy(alpha = 0.9f),
            imageVector = Icons.Default.Close
        )
    }

private fun Part.itemBackgroundColor(isSelected: Boolean): Color = when {
    isSelected -> Color.Blue.copy(alpha = 0.5f)
    status == null -> Color.DarkGray.copy(alpha = 0.8f)
    status.statusCode == "2000"-> Color.Green.copy(alpha = 0.6f)
    status.statusCode == "1030" -> Color.Red.copy(alpha = 0.7f)
    else -> Color.Yellow.copy(alpha = 0.8f)
}

@Composable
fun PartListItem(
    modifier: Modifier = Modifier,
    part: Part,
    isSelected: Boolean
) {

    Row(
        modifier = modifier.fillMaxWidth().background(color = part.itemBackgroundColor(isSelected)).padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(0.64f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = part.partName, fontSize = 22.sp, color = Color.White,)

        Box(modifier = Modifier.weight(0.18f), contentAlignment = Alignment.Center){
            CircularBackgroundIcon(size = 40.dp, bgColor = part.qrIconData.bgColor, imageVector = part.qrIconData.imageVector, iconTint = part.qrIconData.iconColor )
        }

        Box(modifier = Modifier.weight(0.18f), contentAlignment = Alignment.Center){
            CircularBackgroundIcon(size = 40.dp, bgColor = part.resultIconData.bgColor, imageVector = part.resultIconData.imageVector, iconTint = part.resultIconData.iconColor )
        }
    }
}

data class ResponseStatus(
    val statusCode: String
)

data class Part(
    val partName: String,
    val isBarcodeScanned: Boolean = false,
    val isBarcodeRequired: Boolean = false,
    val isProcessing: Boolean = false,
    val isVisible: Boolean = true,
    val status: ResponseStatus? = null
)


@Preview
@Composable
private fun PartListPrev() {
    val partList = listOf(
        Part(partName = "Air Bag", isBarcodeScanned = true),
        Part(partName = "Fuel Label", isBarcodeRequired = true, status = ResponseStatus("5040")),
        Part(partName = "Tyre Pressure Label", status = ResponseStatus("2000")),
        Part(partName = "Colour Label", status = ResponseStatus("1030")),
    )

    PartList(
        modifier = Modifier.fillMaxSize(),
        partList = partList,
        selectedPart = partList[0]
    )
}