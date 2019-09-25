package com.akondi.quandootask.core.navigation

import com.akondi.quandootask.AndroidTest
import com.akondi.quandootask.login.Authenticator
import com.akondi.quandootask.login.LoginActivity
import com.akondi.quandootask.merchants.presentation.activities.MerchantsActivity
import com.akondi.quandootask.shouldNavigateTo
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Mock
    private lateinit var authenticator: Authenticator

    @Before
    fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test
    fun `should forward user to login screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMain(activityContext())

        Mockito.verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test
    fun `should forward user to merchants screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMain(activityContext())

        Mockito.verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo MerchantsActivity::class
    }
}