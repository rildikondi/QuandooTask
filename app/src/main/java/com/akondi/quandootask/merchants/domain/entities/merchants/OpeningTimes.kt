package com.akondi.quandootask.merchants.domain.entities.merchants

data class OpeningTimes(
    val standardOpeningTimes: StandardOpeningTimes
) {
    companion object {
        fun empty() =
            OpeningTimes(StandardOpeningTimes.empty())
    }
}