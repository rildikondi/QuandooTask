package com.akondi.quandootask.features.merchants_feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.core.platform.BaseViewModel
import com.akondi.quandootask.entities.merchants.Merchants
import kotlinx.coroutines.launch
import javax.inject.Inject

class MerchantsViewModel
@Inject constructor(private val getMerchants: GetMerchants) : BaseViewModel() {

    var merchants: MutableLiveData<List<MerchantView>> = MutableLiveData()

    fun loadMerchants() = viewModelScope.launch {
        getMerchants(UseCase.None()) { it.either(::handleFailure, ::handleMerchantList) }
    }

    private fun handleMerchantList(merchants: Merchants) {
        this.merchants.value = merchants.merchants.map { MerchantView(it.id, it.name, it.images[0].url) }
    }
}