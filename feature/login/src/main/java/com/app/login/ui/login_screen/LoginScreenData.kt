package com.app.login.ui.login_screen

data class LoginScreenData(
    val appVersion: String = "",
    val deviceId: String = "",
    val enteredPin: String = "",
    val enteredAdminUser: String = "",
    val enteredAdminPass: String = "",
    val languageList: List<String> = listOf("en"),
    val selectedLanguage: String = "en",
    val isEnteringAdminCreds: Boolean = false
)