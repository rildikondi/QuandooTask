package com.akondi.quandootask.features.merchants_feature

import com.akondi.quandootask.entities.merchantdetails.*

data class MerchantDetailsView(
    val bookable: Boolean,
    val id: Int,
    val images: List<Image>,
    val location: Location,
    val name: String,
    val phoneNumber: String,
    val reviewScore: String
)