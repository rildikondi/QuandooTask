package com.akondi.quandootask.merchants.domain.entities.failures

import com.akondi.quandootask.core.exception.Failure

class MerchantFailure {
    class MerchantsNotAvailable: Failure.FeatureFailure()
    class NonExistentMerchant: Failure.FeatureFailure()
}