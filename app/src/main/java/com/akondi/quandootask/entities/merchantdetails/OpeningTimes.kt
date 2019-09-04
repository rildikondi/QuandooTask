package com.akondi.quandootask.entities.merchantdetails

data class OpeningTimes(
    val standardOpeningTimes: StandardOpeningTimes
) {
    companion object {
        fun empty() =
            OpeningTimes(StandardOpeningTimes.empty())
    }
}