package com.akondi.quandootask.features.merchants_feature

import android.os.Parcel
import com.akondi.quandootask.core.platform.KParcelable
import com.akondi.quandootask.core.platform.parcelableCreator
import com.akondi.quandootask.entities.merchants.Image
import java.util.*


class MerchantView(
    val id: Int,
    val name: String?,
    val images: List<Image>
) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::MerchantView)
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        (parcel.readArray(Image::class.java.classLoader) as Array<Image>).toList()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(name)
            writeList(images)
        }
    }
}