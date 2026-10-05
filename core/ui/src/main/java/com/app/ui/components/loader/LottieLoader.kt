package com.app.ui.components.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Loader Composition Global Controller Class
 */
class LoaderController {
    var isLoading by mutableStateOf(false)
        private set

    fun show() { isLoading = true }
    fun hide() { isLoading = false }
}

/**
 * CompositionLocal for accessing the global [LoaderController] across the composable hierarchy.
 */
val LocalLoaderController = staticCompositionLocalOf { LoaderController() }

/**
 * LottieLoader component that blurs the background content and blocks touch interactions when active.
 *
 * @param modifier Modifier for the root container Box.
 * @param isLoading Controls whether the loader overlay and background blur are active.
 * @param blurRadius Radius for the background blur effect when [isLoading] is true.
 * @param content Optional content lambda containing the UI to be blurred underneath the loader.
 */
@Composable
fun LottieLoader(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    blurRadius: Dp = 16.dp,
    content: (@Composable () -> Unit)? = null
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.fire))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Render and blur background content if provided
        if (content != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(if (isLoading) blurRadius else 0.dp)
            ) {
                content()
            }
        }

        // Render loading overlay and disable touch interactions when active
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {} // Blocks touch/click events from reaching UI underneath
                    .background(
                        brush = Brush.radialGradient(
                            radius = 1800f,
                            colors = listOf(
                                Color.Gray.copy(alpha = 0.3f),
                                Color.Black.copy(alpha = 0.3f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {}

            LottieAnimation(
                modifier = Modifier
                    .size(120.dp)
                    .zIndex(1f),
                composition = composition,
                progress = { progress }
            )
        }
    }
}

@Preview
@Composable
private fun LottieLoaderPrev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        LottieLoader(isLoading = true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TextButton(onClick = {}) {
                    Text("ajbjdajhd dakjdka")
                }
            }
        }
    }
}

@Preview
@Composable
private fun LottieLoaderCompositionLocalPrev() {
    val loaderController = remember { LoaderController() }

    CompositionLocalProvider(LocalLoaderController provides loaderController) {
        LottieLoader(isLoading = loaderController.isLoading) {
            val controller = LocalLoaderController.current
            val scope = rememberCoroutineScope()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextButton(onClick = {}) {
                        Text("Interactive UI Element")
                    }
                    Button(
                        onClick = {
                            scope.launch {
                                controller.show()
                                delay(3000)
                                controller.hide()
                            }
                        }
                    ) {
                        Text("Show Global Loader (3s)")
                    }
                }
            }
        }
    }
}
