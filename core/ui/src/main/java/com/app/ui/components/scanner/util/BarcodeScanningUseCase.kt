package com.app.ui.components.scanner.util

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.ByteBuffer
import kotlin.experimental.inv
import kotlin.system.measureTimeMillis


class BarcodeScanningUseCase(
    val onBarcodesDetected: (List<Barcode>) -> Unit,
    val coroutineScope: CoroutineScope
) : ImageAnalysis.Analyzer {

    private val TAG = "BarcodeScanningUseCase"
    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()
    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        coroutineScope.launch {
            val executionTimeMs = measureTimeMillis {
                val job1 = launch { processImageProxy(imageProxy) }
                val job2 = launch { processInvertedImageProxy(imageProxy) }

                joinAll(job1, job2)
            }
//            Log.d(TAG, "Barcode analysis execution time: $executionTimeMs ms")
            imageProxy.close()
        }
    }

    @OptIn(ExperimentalGetImage::class)
    suspend fun processImageProxy(imageProxy: ImageProxy) = withContext(Dispatchers.Default){
        imageProxy.image?.let { image ->
            val imageToAnalyse = InputImage.fromMediaImage(
                image,
                imageProxy.imageInfo.rotationDegrees
            )

            scanner.process(imageToAnalyse)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()){
                        onBarcodesDetected(barcodes)
                    }
                }
                .addOnFailureListener { ex ->
                    Log.d(TAG, "processImageProxy: failed to read barcode with exception : $ex")
                }
        }
    }

    @OptIn(ExperimentalGetImage::class)
    suspend fun processInvertedImageProxy(imageProxy: ImageProxy) = withContext(Dispatchers.Default){
        imageProxy.image?.let { mediaImage ->

            // 1. Get the Y-plane (Luminance channel) which holds the brightness data
            val yPlane = mediaImage.planes[0]
            val yBuffer: ByteBuffer = yPlane.buffer

            // 2. Rewind the buffer to ensure we read from the beginning
            yBuffer.rewind()
            val limit = yBuffer.remaining()

            // 3. Extract the raw bytes into an array
            val yBytes = ByteArray(limit)
            yBuffer.get(yBytes)

            // 4. Invert the Y-channel using bitwise NOT (inv)
            // This flips white modules to black and vice versa instantly.
            for (i in 0 until limit) {
                yBytes[i] = yBytes[i].inv()
            }

            // 5. Write the inverted bytes back into the original buffer
            yBuffer.rewind()
            yBuffer.put(yBytes)

            // 6. Pass the directly modified MediaImage straight to ML Kit
            // No Bitmap conversions or JPEG byte arrays needed!
            val imageToAnalyse = InputImage.fromMediaImage(
                mediaImage,
                imageProxy.imageInfo.rotationDegrees
            )

            scanner.process(imageToAnalyse)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()){
                        onBarcodesDetected(barcodes)
                    }
                }
                .addOnFailureListener { ex ->
                    Log.d(TAG, "processImageProxy: failed to read barcode with exception : $ex")
                }
        }
    }
}