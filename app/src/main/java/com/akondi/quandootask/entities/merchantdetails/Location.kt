package com.akondi.quandootask.entities.merchantdetails

data class Location(
    val address: Address,
    val coordinates: Coordinates
) {
    companion object {
        fun empty() = Location(
            Address.empty(),
            Coordinates.empty()
        )
    }
}