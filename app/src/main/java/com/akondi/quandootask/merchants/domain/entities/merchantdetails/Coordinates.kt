package com.akondi.quandootask.merchants.domain.entities.merchantdetails


data class Coordinates(
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        fun empty() = Coordinates(0.0, 0.0)
    }
}