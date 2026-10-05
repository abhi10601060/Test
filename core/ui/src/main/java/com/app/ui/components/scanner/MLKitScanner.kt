package com.app.ui.components.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.app.ui.components.scanner.util.BarcodeScanningUseCase
import com.google.mlkit.vision.barcode.common.Barcode

/**
 * Camera Command class which controls the scanner and camera activities.
 */
data class MLKitScannerCommand(
    val enableTorch: Boolean = false,
    val takePicture: Boolean = false,
    val zoomValue: Float = 1f,
    val enableBarcodeScanning: Boolean = false
)

/**
 * Camera preview composable with runtime camera permission check, camera lifecycle binding,
 * torch toggle, and picture capture command handling with high-quality capture.
 */
@Composable
fun MLKitScanner(
    modifier: Modifier = Modifier,
    mlKitScannerCommand: MLKitScannerCommand = MLKitScannerCommand(),
    onImageCaptured: (image: Bitmap) -> Unit = {},
    onBarcodeDetected: (List<Barcode>) -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    val barcodeScanningUseCase = remember {
        BarcodeScanningUseCase(
            onBarcodesDetected = onBarcodeDetected,
            coroutineScope = coroutineScope
        )
    }

    // Permission Handling
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // Single LifecycleCameraController instance configured for maximum image capture quality
    val cameraController = remember(context) {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE or CameraController.IMAGE_ANALYSIS)
            imageCaptureMode = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY // Maximum Resolution & Quality
            imageAnalysisBackpressureStrategy = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
            bindToLifecycle(lifecycleOwner)
        }
    }

    // Toggle torch whenever command changes and permission is granted
    LaunchedEffect(mlKitScannerCommand.enableTorch, hasCameraPermission) {
        if (hasCameraPermission) {
            cameraController.enableTorch(mlKitScannerCommand.enableTorch)
        }
    }

    // Handle picture capture command
    LaunchedEffect(mlKitScannerCommand.takePicture, hasCameraPermission) {
        if (mlKitScannerCommand.takePicture && hasCameraPermission) {
            cameraController.takePicture(
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        val bitmap = image.toBitmap()
                        image.close()
                        onImageCaptured(bitmap)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        // Image capture error handling
                    }
                }
            )
        }
    }

    // Handle Barcode Scanning
    LaunchedEffect(mlKitScannerCommand.enableBarcodeScanning, hasCameraPermission) {
        if (hasCameraPermission){
            if (mlKitScannerCommand.enableBarcodeScanning){
                cameraController.setImageAnalysisAnalyzer(ContextCompat.getMainExecutor(context), barcodeScanningUseCase)
            }
            else{
                cameraController.clearImageAnalysisAnalyzer()
            }
        }
    }

    // Handle zoom ratio
    LaunchedEffect(mlKitScannerCommand.zoomValue, hasCameraPermission) {
        if (mlKitScannerCommand.zoomValue > 0f && hasCameraPermission){
            cameraController.setZoomRatio(mlKitScannerCommand.zoomValue)
        }
    }

    // Actual View
    if (hasCameraPermission) {
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    this.controller = cameraController
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                }
            },
            modifier = modifier.fillMaxSize()
        )
    } else {
        Box(
            modifier = modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Camera permission is required to scan codes.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
                    Text(text = "Grant Camera Permission")
                }
            }
        }
    }
}
