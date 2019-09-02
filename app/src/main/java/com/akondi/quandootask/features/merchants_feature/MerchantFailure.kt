package com.akondi.quandootask.features.merchants_feature

import com.akondi.quandootask.core.exception.Failure

class MerchantFailure {
    class MerchantsNotAvailable: Failure.FeatureFailure()
    class NonExistentMerchant: Failure.FeatureFailure()
}