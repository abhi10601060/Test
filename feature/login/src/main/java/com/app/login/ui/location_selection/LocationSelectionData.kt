package com.app.login.ui.location_selection

data class LocationSelectionData(
    val locations: List<String> = listOf("cal 1", "cal 2", "cal 3"),
    val shifts: List<String> = listOf("red", "blue", "orange"),
    val groups: List<String> = listOf("RHF", "LHF"),
    val selectedLocation: String = "",
    val selectedShift: String = "",
    val selectedGroups: List<String> = listOf(),
    val activities: List<String> = listOf(),
    val selectedActivity: String? = null,
)
