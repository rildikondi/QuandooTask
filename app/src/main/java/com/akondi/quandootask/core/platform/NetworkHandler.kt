package com.akondi.quandootask.core.platform

import android.content.Context
import com.akondi.quandootask.core.extensions.connectivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which returns information about the network connection state.
 */
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected : Boolean get() = context.connectivity
}