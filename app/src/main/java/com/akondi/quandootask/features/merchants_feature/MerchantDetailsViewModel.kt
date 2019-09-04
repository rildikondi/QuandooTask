package com.akondi.quandootask.features.merchants_feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.quandootask.core.platform.BaseViewModel
import com.akondi.quandootask.entities.merchantdetails.MerchantDetails
import kotlinx.coroutines.launch
import javax.inject.Inject

class MerchantDetailsViewModel
@Inject constructor(
    private val getMerchantDetails: GetMerchantDetails,
    private val playMerchant: PlayMerchant
) : BaseViewModel() {

    var merchantDetails: MutableLiveData<MerchantDetailsView> = MutableLiveData()

    fun loadMerchantDetails(merchantId: Int) = viewModelScope.launch {
        getMerchantDetails(GetMerchantDetails.Params(merchantId)) {
            it.either(
                ::handleFailure,
                ::handleMerchantDetails
            )
        }
    }

    fun playMerchant(url: String) = viewModelScope.launch {
        playMerchant(PlayMerchant.Params(url))
    }

    private fun handleMerchantDetails(merchant: MerchantDetails) {
        this.merchantDetails.value = MerchantDetailsView(
            merchant.bookable,
            merchant.id,
            merchant.images,
            merchant.location,
            merchant.name,
            merchant.phoneNumber,
            merchant.reviewScore
        )
    }
}