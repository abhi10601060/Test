package com.app.ui.components.scanner

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ScannerTestScreen(modifier: Modifier = Modifier) {
    var scannerCommand by remember { mutableStateOf(MLKitScannerCommand()) }
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showCapturedImageDialog by remember { mutableStateOf(false) }

    var scannedValue by remember { mutableStateOf("barcode value") }

    Column(modifier = modifier.fillMaxSize().background(color = Color.White)) {
        MLKitScanner(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            mlKitScannerCommand = scannerCommand,
            onImageCaptured = { bitmap ->
                capturedBitmap = bitmap
                // Reset takePicture flag after capturing
                scannerCommand = scannerCommand.copy(takePicture = false)
            },
            onBarcodeDetected = { barcodes ->
                barcodes.map { barcode ->
                    barcode.rawValue?.let {
                        scannedValue = it
                    }
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.14f)
                .background(color = Color.DarkGray)
                .padding(8.dp)
        )
        {
            // Upper Row (Left: Flash toggle, Middle: Capture, Right: Scan toggle)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Button: Turn ON / OFF Flash Toggle
                IconButton(
                    onClick = {
                        scannerCommand = scannerCommand.copy(enableTorch = !scannerCommand.enableTorch)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (scannerCommand.enableTorch) Color.Yellow else Color.Gray,
                        contentColor = if (scannerCommand.enableTorch) Color.Black else Color.White
                    )
                ) {
                    Icon(
                        imageVector = if (scannerCommand.enableTorch) Icons.Default.FlashOn else Icons.Default.FlashOff,
                        contentDescription = "Flash Toggle"
                    )
                }

                // Middle Button: Capture
                IconButton(
                    onClick = {
                        scannerCommand = scannerCommand.copy(takePicture = true)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Capture"
                    )
                }

                // Right Button: Turn ON / OFF Scan Toggle
                IconButton(
                    onClick = { scannerCommand = scannerCommand.copy(enableBarcodeScanning = !scannerCommand.enableBarcodeScanning) },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (scannerCommand.enableBarcodeScanning) Color(0xFF4CAF50) else Color.Gray,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "Scan Toggle"
                    )
                }
            }

            // Lower Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // View Captured Image Button
                IconButton(
                    onClick = { if (scannerCommand.zoomValue < 3) scannerCommand = scannerCommand.copy(zoomValue = scannerCommand.zoomValue + 1) },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White,
                        disabledContainerColor = Color.DarkGray,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ZoomIn,
                        contentDescription = "Zoom in"
                    )
                }


                // View Captured Image Button
                IconButton(
                    onClick = { if (scannerCommand.zoomValue > 1) scannerCommand = scannerCommand.copy(zoomValue = scannerCommand.zoomValue - 1) },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White,
                        disabledContainerColor = Color.DarkGray,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ZoomOut,
                        contentDescription = "Zoom out"
                    )
                }


                // View Captured Image Button
                IconButton(
                    onClick = { showCapturedImageDialog = true },
                    enabled = capturedBitmap != null,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (capturedBitmap != null) Color(0xFF2196F3) else Color.Gray,
                        contentColor = Color.White,
                        disabledContainerColor = Color.DarkGray,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "View Captured Image"
                    )
                }
            }
        }

        Text(modifier = Modifier.fillMaxWidth().background(color = Color.DarkGray).weight(0.06f).padding(4.dp),text = scannedValue, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }

    // Popup Dialog displaying the captured bitmap
    if (showCapturedImageDialog && capturedBitmap != null) {
        Box(modifier = Modifier.padding(top = 100.dp).fillMaxSize().background(color = Color.DarkGray)) {
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Captured Image",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    bitmap = capturedBitmap!!.asImageBitmap(),
                    contentDescription = "Captured Image Preview",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = { showCapturedImageDialog = false }) {
                    Text(text = "Close", color = Color(0xFF64B5F6), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
