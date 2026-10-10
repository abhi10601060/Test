package com.app.login.ui.location_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardReturn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.buttons.RegularImageButton
import com.app.ui.components.buttons.RegularTextButton
import com.app.ui.components.radio_buttons.HorizontalScrollableRadioButtons

@Composable
fun ActivitySelectionPopup(
    modifier: Modifier = Modifier,
    locationSelectionData: LocationSelectionData,
    onActivitySelected: (Int, String) -> Unit,
    onBackPressed: () -> Unit,
    onConfirmPressed: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center,
            text = "Select Use case", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier.fillMaxSize().weight(1f).padding(50.dp),
            contentAlignment = Alignment.Center
        ){
            HorizontalScrollableRadioButtons(
                modifier= Modifier
                    .background(shape = RoundedCornerShape(15.dp), color = Color.DarkGray)
                    .padding(vertical = 30.dp, horizontal = 15.dp),
                items = locationSelectionData.activities,
                itemsString = {if(it.length > 10) it.replace(" ", "\n") else it},
                onItemSelected = onActivitySelected,
                selectedItem = locationSelectionData.selectedActivity,
                fontSize = 32.sp,
                maxLines = 2
            )
        }

        Row(
            modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RegularImageButton(
                icon = Icons.Default.KeyboardReturn,
                onClick = onBackPressed
            )

            RegularTextButton(
                text = "Confirm",
                onClick = onConfirmPressed
            )
        }
    }
}

@LandscapePreview
@Composable
private fun ActivitySelectionPopupPrev() {
    ActivitySelectionPopup(
        locationSelectionData = LocationSelectionData(activities = listOf("Label Inspection", "a b", "Paint Inspection", "Users", "RFID Validation")),
        onActivitySelected = {_, _ -> },
        onBackPressed = {},
        onConfirmPressed = {}
    )
}