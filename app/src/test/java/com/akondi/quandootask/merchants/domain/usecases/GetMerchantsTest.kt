package com.akondi.quandootask.merchants.domain.usecases

import com.akondi.quandootask.UnitTest
import com.akondi.quandootask.core.functional.Either
import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.merchants.domain.entities.merchants.Merchants
import com.akondi.quandootask.merchants.domain.repository.MerchantsRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMerchantsTest : UnitTest() {

    private lateinit var getMerchants: GetMerchants

    @Mock
    private lateinit var merchantsRepository: MerchantsRepository

    @Before
    fun setUp() {
        getMerchants = GetMerchants(merchantsRepository)
        given { merchantsRepository.merchants() }.willReturn(Either.Right(Merchants.empty()))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMerchants.run(UseCase.None()) }

        verify(merchantsRepository).merchants()
        verifyNoMoreInteractions(merchantsRepository)
    }
}