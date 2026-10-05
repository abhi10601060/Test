package com.app.ui.components.alert

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class AlertType {
    WARNING, ERROR, INFO
}

data class AlertData(
    val alertType: AlertType,
    val title: String,
    val message: String,
    val positiveButtonTitle: String? = null,
    val onPositiveButtonClick: () -> Unit = {},
    val negativeButtonTitle: String? = null,
    val onNegativeButtonClick: () -> Unit = {}
)

/**
 * A reusable AlertDialog component displaying an icon, title, message, and positive/negative text buttons with custom colors.
 *
 * @param modifier Modifier for the AlertDialog.
 * @param alertData Holds data for icon type, title, message, and button titles/callbacks.
 * @param containerColor Background color for the dialog card.
 * @param titleColor Color for the title text.
 * @param messageColor Color for the body message text.
 * @param positiveButtonColor Text color for the confirm button.
 * @param negativeButtonColor Text color for the dismiss button.
 * @param onDismissRequest Callback when the dialog is dismissed via back gesture or outside touch.
 */
@Composable
fun Alert(
    modifier: Modifier = Modifier,
    alertData: AlertData,
    containerColor: Color = Color(0xFF2C2C2C),
    titleColor: Color = Color.White,
    messageColor: Color = Color(0xFFE0E0E0),
    positiveButtonColor: Color? = null,
    negativeButtonColor: Color = Color(0xFFB0BEC5),
    onDismissRequest: () -> Unit = {}
) {
    val icon = when (alertData.alertType) {
        AlertType.INFO -> Icons.Outlined.Info
        AlertType.ERROR -> Icons.Outlined.Cancel
        AlertType.WARNING -> Icons.Outlined.Warning
    }

    val iconColor = when (alertData.alertType) {
        AlertType.INFO -> Color(0xFF2196F3)
        AlertType.ERROR -> Color(0xFFE53935)
        AlertType.WARNING -> Color(0xFFFFA000)
    }

    val resolvedPositiveColor = positiveButtonColor ?: iconColor

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = containerColor,
        titleContentColor = titleColor,
        textContentColor = messageColor,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = alertData.alertType.name,
                tint = iconColor,
                modifier = Modifier.size(40.dp)
            )
        },
        title = {
            Text(text = alertData.title, color = titleColor, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = alertData.message, color = messageColor)
        },
        confirmButton = {
            alertData.positiveButtonTitle?.let { positiveText ->
                TextButton(
                    onClick = {
                        alertData.onPositiveButtonClick()
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = resolvedPositiveColor)
                ) {
                    Text(text = positiveText, fontWeight = FontWeight.SemiBold)
                }
            }
        },
        dismissButton = {
            alertData.negativeButtonTitle?.let { negativeText ->
                TextButton(
                    onClick = {
                        alertData.onNegativeButtonClick()
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = negativeButtonColor)
                ) {
                    Text(text = negativeText)
                }
            }
        }
    )
}

@Preview
@Composable
private fun AlertPrev() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Alert(
            alertData = AlertData(
                alertType = AlertType.WARNING,
                title = "Confirm Action",
                message = "Are you sure you want to perform this action? This step cannot be undone.",
                positiveButtonTitle = "Confirm",
                negativeButtonTitle = "Cancel"
            )
        )
    }
}
