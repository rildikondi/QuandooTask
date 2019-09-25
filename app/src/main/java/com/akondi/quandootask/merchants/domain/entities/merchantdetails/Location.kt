package com.akondi.quandootask.merchants.domain.entities.merchantdetails

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