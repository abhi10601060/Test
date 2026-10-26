package com.app.login.ui.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.KeyboardReturn
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.login.ui.location_selection.LandscapePreview
import com.app.ui.components.buttons.RegularImageButton
import com.app.ui.components.dialog.TextInputDialog
import com.app.ui.components.scanner.MLKitScannerCommand


@Composable
fun ScannerScreen(modifier: Modifier = Modifier) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreenUi(
    modifier: Modifier = Modifier
) {

    var scannerCommand by remember { mutableStateOf(MLKitScannerCommand()) }
    var showAlert by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        MLKitScanner(
//            modifier = Modifier.fillMaxSize(),
//            mlKitScannerCommand = MLKitScannerCommand(),
//            onBarcodeDetected = {barcodes -> },
//            onImageCaptured = {
//                scannerCommand = scannerCommand.copy(takePicture =  false)
//
//            }
//        )

        Row(
            modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 100.dp, vertical = 30.dp)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(40.dp),
                    color = Color.DarkGray.copy(alpha = 0.2f)
                )
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            RegularImageButton(icon = Icons.Default.KeyboardReturn, onClick = {})
            RegularImageButton(icon = Icons.Default.ZoomOut, onClick = {
                if (scannerCommand.zoomValue > 1) scannerCommand = scannerCommand.copy(zoomValue = scannerCommand.zoomValue - 1)
            })

            Icon(
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        scannerCommand = scannerCommand.copy(takePicture = true)
                    },
                imageVector = Icons.Default.Camera,
                contentDescription = "Capture Button",
                tint = Color.Blue
            )

            RegularImageButton(icon = Icons.Default.ZoomIn, onClick = {
                if (scannerCommand.zoomValue < 3) scannerCommand = scannerCommand.copy(zoomValue = scannerCommand.zoomValue + 1)
            })
            RegularImageButton(icon = if (scannerCommand.enableTorch) Icons.Default.FlashOff else Icons.Default.FlashOn, onClick = {
                scannerCommand = scannerCommand.copy(enableTorch = !scannerCommand.enableTorch)
            })
        }

        if (showAlert){
            TextInputDialog(
                title = "Captured identifier",
                value = "",
                onValueChange = {},
                onCloseClick = {},
                onYesClick = {}
            )
        }
    }
}



@LandscapePreview
@Composable
private fun ScannerScreenPrev() {
    ScannerScreenUi()
}