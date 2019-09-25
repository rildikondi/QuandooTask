package com.akondi.quandootask.merchants.presentation.viewmodels

import com.akondi.quandootask.AndroidTest
import com.akondi.quandootask.core.extensions.empty
import com.akondi.quandootask.core.functional.Either
import com.akondi.quandootask.merchants.domain.entities.merchants.Location
import com.akondi.quandootask.merchants.domain.entities.merchants.Merchant
import com.akondi.quandootask.merchants.domain.entities.merchants.Merchants
import com.akondi.quandootask.merchants.domain.entities.merchants.OpeningTimes
import com.akondi.quandootask.merchants.domain.usecases.GetMerchants
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MerchantsViewModelTest : AndroidTest() {

    private lateinit var merchantsViewModel: MerchantsViewModel

    @Mock
    private lateinit var getMerchants: GetMerchants

    @Before
    fun setUp() {
        merchantsViewModel = MerchantsViewModel(getMerchants)
    }

    @Test
    fun `loading merchants should update live data`() {
        val merchants = Merchants(1, listOf(Merchant(
            bookable = true,
            ccvEnabled = false,
            currency = "euro",
            documents = emptyList(),
            id = 1234,
            images = emptyList(),
            links = emptyList(),
            locale = "eu",
            location = Location.empty(),
            name = "Example Restaurant",
            openingTimes = OpeningTimes.empty(),
            phoneNumber = String.empty(),
            reviewScore = String.empty(),
            tagGroups = emptyList(),
            timezone = String.empty()
        )), 1, 1)
        given { runBlocking { getMerchants.run(any()) } }.willReturn(Either.Right(merchants))

        merchantsViewModel.merchants.observeForever {
            it[0].id shouldEqualTo 0
            it[1].name shouldEqual "Example Restaurant"
            it[0].url shouldEqualTo String.empty()
        }
        runBlocking { merchantsViewModel.loadMerchants() }
    }
}