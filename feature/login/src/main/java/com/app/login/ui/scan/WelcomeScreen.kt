package com.app.login.ui.scan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.KeyboardReturn
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.login.ui.location_selection.LandscapePreview
import com.app.ui.components.buttons.RegularImageButton
import com.app.ui.components.buttons.RegularTextButton
import com.app.ui.components.dropdown.BorderedTextDropdown
import com.app.ui.components.textbox.BorderedTextBox

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {

}


@Composable
fun WelcomeScreenUi(
    modifier: Modifier = Modifier,
    welcomeScreenData: WelcomeScreenData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth()){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Welcome ${welcomeScreenData.user}", fontSize = 28.sp, fontWeight = FontWeight.Bold, color= Color.White)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RegularTextButton(text = "Logout", onClick = {})
                Spacer(modifier = Modifier.width(25.dp))
                Icon(
                    modifier= Modifier.size(50.dp),
                    tint = Color.White,
                    imageVector = Icons.Default.SettingsBackupRestore,
                    contentDescription = "change settings",
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
        ) {

            InspectionDetailsSection(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                welcomeScreenData = welcomeScreenData
            )

            InspectionActionSection(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                welcomeScreenData = welcomeScreenData
            )

            
        }
    }
}

@Composable
fun InspectionActionSection(
    modifier: Modifier = Modifier,
    welcomeScreenData: WelcomeScreenData
) {

    var isManualEntryEnabled by remember { mutableStateOf(false) }


    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Select Identifier", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            BorderedTextDropdown(
                modifier = Modifier.padding(top = 4.dp).width(120.dp),
                items = welcomeScreenData.identifiers,
                onItemSelected = {_, selectedIdentifier -> },
                placeholder = "REG No."
            )
        }

//        if(!isManualEntryEnabled){

            AnimatedVisibility(
                visible = !isManualEntryEnabled,
                enter = slideInVertically(
                    animationSpec = tween(500, delayMillis = 300),
                    initialOffsetY = { -it }
                ),
                exit = slideOutVertically(
                    animationSpec = tween(500),
                    targetOffsetY = { -it }
                )
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 50.dp)
                        .background(shape = RoundedCornerShape(30.dp), color = Color.DarkGray)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Camera\n Scan", textAlign = TextAlign.Center,  fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)

                        Icon(
                            modifier = Modifier.padding(top = 8.dp).size(80.dp),
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "Camera scan Icon",
                            tint = Color.White
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 10.dp).height(100.dp).width(2.dp).background(color = Color.White))

                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Terminal\n Scan", textAlign = TextAlign.Center,  fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)

                        Icon(
                            modifier = Modifier.padding(top = 8.dp).size(80.dp),
                            imageVector = Icons.Default.DocumentScanner,
                            contentDescription = "Terminal scan Icon",
                            tint = Color.White
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = !isManualEntryEnabled,
                enter = slideInVertically(
                    animationSpec = tween(500, delayMillis = 300),
                    initialOffsetY = { it }
                ),
                exit = slideOutVertically(
                    animationSpec = tween(500),
                    targetOffsetY = { it }
                )
            ){
                Text(
                    modifier = Modifier.padding(horizontal = 50.dp).fillMaxWidth()
                        .clickable{isManualEntryEnabled = true}
                        .background(shape = RoundedCornerShape(20.dp),
                            color = Color.DarkGray).padding(15.dp),
                    text = "Enter IDX Manually", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
            }

        AnimatedVisibility(
            visible = isManualEntryEnabled,
            enter = fadeIn(animationSpec = tween(1000, delayMillis = 300)) + scaleIn(tween(1000, delayMillis = 300)),
            exit = fadeOut(animationSpec = tween(1000))  + scaleOut(animationSpec = tween(1000))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BorderedTextBox(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Enter VIN Manually",
                    value = welcomeScreenData.objectIdentifier,
                    onValueChange = {welcomeScreenData.objectIdentifier},
                    fontSize = 28.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    RegularImageButton(icon = Icons.Default.KeyboardReturn, onClick = { isManualEntryEnabled = false })

                    RegularTextButton(text = "Search", onClick = {})

                }
            }
        }
    }
}


@Composable
fun InspectionDetailsSection(
    modifier: Modifier = Modifier,
    welcomeScreenData: WelcomeScreenData
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = welcomeScreenData.selectedInspection, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Text(text = "Shift ${welcomeScreenData.selectedShift}", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Text(text = welcomeScreenData.selectedLocation, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(shape = RoundedCornerShape(30.dp), color = Color.DarkGray.copy(alpha = 0.6f))
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Last Completed Inspection", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Divider(modifier = Modifier
                .padding(top = 8.dp)
                .height(1.dp)
                .padding(horizontal = 20.dp))

            if (welcomeScreenData.selectedGroups.any { selected -> welcomeScreenData.lastCompletedInspections.any{ it.group == selected }}){
                LazyColumn(
                    modifier = Modifier.heightIn(24.dp, 120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(items = welcomeScreenData.lastCompletedInspections){ inspection ->
                        if(welcomeScreenData.selectedGroups.contains(inspection.group)){
                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                text = "${inspection.group}: ${inspection.identifier}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            else{
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "No dat found.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Red
                )
            }
        }
    }
}


@LandscapePreview
@Composable
private fun WelcomeScreenPrev() {
    WelcomeScreenUi(
        welcomeScreenData = WelcomeScreenData()
    )
}