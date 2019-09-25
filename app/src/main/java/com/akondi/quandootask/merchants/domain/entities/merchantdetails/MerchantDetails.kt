package com.akondi.quandootask.merchants.domain.entities.merchantdetails
import com.akondi.cleancoremvvm.core.extension.empty
import com.akondi.quandootask.merchants.domain.entities.merchants.Image

data class MerchantDetails(
    val bookable: Boolean,
    val ccvEnabled: Boolean,
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
        fun empty() = MerchantDetails(
            false,
            false,
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
}