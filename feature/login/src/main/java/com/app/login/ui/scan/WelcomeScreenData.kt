package com.app.login.ui.scan

data class WelcomeScreenData(
    val user: String = "testuser2",
    val selectedInspection: String = "Label Inspection",
    val selectedShift: String = "Red",
    val selectedGroups: List<String> = listOf("RH", "LH", "FR/R"),
    val selectedLocation: String = "Bay Area",
    val identifiers: List<String> = listOf("VIN", "BSN", "USN"),
    val lastCompletedInspections: List<LastCompletedInspection> = listOf(LastCompletedInspection("RH", "ABCDEFG123467890"), LastCompletedInspection("FR/R", "ABCDEFG0987654321"), LastCompletedInspection("SH", "ABCDEFG123467890")),
    val objectIdentifier: String = ""
)

data class LastCompletedInspection(
    val group: String,
    val identifier: String
)