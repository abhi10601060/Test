package com.app.login.ui.location_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.buttons.RegularTextButton
import com.app.ui.components.checkbox.HorizontalScrollableCheckBox
import com.app.ui.components.radio_buttons.HorizontalScrollableRadioButtons

@Composable
fun LocationSelectionScreen(modifier: Modifier = Modifier) {

}

@Composable
fun LocationSelectionScreenUi(
    modifier: Modifier = Modifier,
    locationSelectionData: LocationSelectionData
) {

    Column(
        modifier = modifier.fillMaxSize().background(color = Color.Black).padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        RegularTextButton(
            modifier = Modifier.padding(start = 10.dp),
            text = "Logout", onClick = {})

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.2f),
                text = "Location",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalScrollableRadioButtons(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.8f)
                    .padding(horizontal = 10.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.DarkGray)
                    .padding(vertical = 15.dp, horizontal = 10.dp),
                items = locationSelectionData.locations,
                itemsString = {it},
                onItemSelected = {_, _ ->},
                selectedItem = locationSelectionData.selectedLocation
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.2f),
                text = "Shift",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalScrollableRadioButtons(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.8f)
                    .padding(horizontal = 10.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.DarkGray)
                    .padding(vertical = 15.dp, horizontal = 10.dp),
                items = locationSelectionData.shifts,
                itemsString = {it},
                onItemSelected = {_, _ ->},
                selectedItem = locationSelectionData.selectedShift
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.2f),
                text = "Groups",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalScrollableCheckBox(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.8f)
                    .padding(horizontal = 10.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.DarkGray)
                    .padding(vertical = 15.dp, horizontal = 10.dp),
                items = locationSelectionData.groups,
                itemsString = {it},
                onItemSelected = {_, _ ->},
                selectedItems = locationSelectionData.selectedGroups
            )
        }

        RegularTextButton(
            modifier = Modifier.align(Alignment.End)
                .padding(end = 10.dp),
            text = "Confirm", onClick = {})

    }

}

@Preview(device ="spec:width=411dp,height=891dp,orientation=landscape")
annotation class LandscapePreview()

@Preview(device ="spec:width=411dp,height=891dp,orientation=landscape")
@Composable
private fun LocationSelectionScreenPrev() {
    LocationSelectionScreenUi(locationSelectionData = LocationSelectionData())
}