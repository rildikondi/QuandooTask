package com.akondi.quandootask.entities.merchants

data class StandardOpeningTimes(
    val FRIDAY: List<FRIDAY>,
    val SATURDAY: List<SATURDAY>,
    val THURSDAY: List<THURSDAY>,
    val TUESDAY: List<TUESDAY>,
    val WEDNESDAY: List<WEDNESDAY>
)