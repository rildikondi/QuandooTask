package com.akondi.quandootask.merchants.domain.entities.merchants

import com.akondi.cleancoremvvm.core.extension.empty

data class Address(
    val country: String
) {
    companion object {
        fun empty() = Address(String.empty())
    }
}