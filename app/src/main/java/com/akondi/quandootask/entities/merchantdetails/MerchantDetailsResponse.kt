package com.akondi.quandootask.entities.merchantdetails

import com.akondi.cleancoremvvm.core.extension.empty

data class MerchantDetailsResponse(
    val bookable: Boolean,
    val ccvEnabled: Boolean,
    val chain: Chain,
    val currency: String,
    val documents: List<Any>,
    val id: Int,
    val images: List<Image>,
    val links: List<Link>,
    val locale: String,
    val location: Location,
    val name: String,
    val openingTimes: OpeningTimes,
    val phoneNumber: String,
    val reviewScore: String,
    val tagGroups: List<TagGroup>,
    val timezone: String
) {
    companion object {
        fun empty() = MerchantDetailsResponse(
            false,
            false,
            Chain(0, String.empty()),
            String.empty(),
            emptyList(),
            0,
            emptyList(),
            emptyList(),
            String.empty(),
            Location.empty(),
            String.empty(),
            OpeningTimes.empty(),
            String.empty(),
            String.empty(),
            emptyList(),
            String.empty()
        )
    }

    fun toMerchantDetails() = MerchantDetails(
        bookable,
        ccvEnabled,
        chain,
        currency,
        documents,
        id,
        images,
        links,
        locale,
        location,
        name,
        openingTimes,
        phoneNumber,
        reviewScore,
        tagGroups,
        timezone
    )
}