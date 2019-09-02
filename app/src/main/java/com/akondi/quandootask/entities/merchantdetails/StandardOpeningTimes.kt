package com.akondi.quandootask.entities.merchantdetails

data class StandardOpeningTimes(
    val SATURDAY: List<SATURDAY>,
    val SUNDAY: List<SUNDAY>
){
    companion object {
        fun empty() = StandardOpeningTimes (
            emptyList(),
            emptyList()
        )
    }
}