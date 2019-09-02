package com.akondi.quandootask.entities.merchantdetails

import com.akondi.cleancoremvvm.core.extension.empty

data class Address(
    val city: String,
    val country: String,
    val number: String,
    val street: String,
    val zipcode: String
) {
    companion object {
        fun empty() = Address(String.empty(), String.empty(), String.empty(), String.empty(), String.empty())
    }
}