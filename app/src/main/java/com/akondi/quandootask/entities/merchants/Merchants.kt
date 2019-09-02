package com.akondi.quandootask.entities.merchants

data class Merchants(
    val limit: Int,
    val merchants: List<Merchant>,
    val offset: Int,
    val size: Int
)