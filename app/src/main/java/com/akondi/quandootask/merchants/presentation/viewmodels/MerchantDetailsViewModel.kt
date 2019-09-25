package com.akondi.quandootask.merchants.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.quandootask.core.platform.BaseViewModel
import com.akondi.quandootask.merchants.domain.usecases.GetMerchantDetails
import com.akondi.quandootask.merchants.domain.entities.merchantdetails.MerchantDetailsView
import com.akondi.quandootask.merchants.domain.usecases.PlayMerchant
import com.akondi.quandootask.merchants.domain.entities.merchantdetails.MerchantDetails
import com.akondi.quandootask.merchants.domain.usecases.GetMerchantDetails.Params
import kotlinx.coroutines.launch
import javax.inject.Inject

class MerchantDetailsViewModel
@Inject constructor(
    private val getMerchantDetails: GetMerchantDetails,
    private val playMerchant: PlayMerchant
) : BaseViewModel() {

    var merchantDetails: MutableLiveData<MerchantDetailsView> = MutableLiveData()

    fun loadMerchantDetails(merchantId: Int) = viewModelScope.launch {
        getMerchantDetails(Params(merchantId)) {
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
        this.merchantDetails.value =
            MerchantDetailsView(
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