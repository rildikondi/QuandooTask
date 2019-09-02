package com.akondi.quandootask.features.merchants

import com.akondi.quandootask.entities.merchants.MerchantsResponse

sealed class MerchantsState {
    class ShowMerchants(val merchantsResponse: MerchantsResponse) : MerchantsState()
    class ShowMessage(val message: String) : MerchantsState()
}