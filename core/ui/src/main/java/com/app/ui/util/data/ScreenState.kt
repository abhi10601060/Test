package com.app.ui.util.data

import com.app.ui.components.alert.AlertData


data class ScreenState <T>(
    val data: T,
    val isLoading: Boolean = false,
    val error: AlertData? = null
)