package com.akondi.quandootask.features.merchants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akondi.quandootask.core.extensions.ScreenState
import com.akondi.quandootask.entities.merchants.MerchantsResponse

class MerchantsViewModel (private val findMerchantsInteractor: FindMerchantsInteractor) : ViewModel() {

    private lateinit var _merchantsState: MutableLiveData<ScreenState<MerchantsState>>

    val merchantsState: LiveData<ScreenState<MerchantsState>>
    get() {
        if(!::_merchantsState.isInitialized) {
            _merchantsState = MutableLiveData()
            _merchantsState.value = ScreenState.Loading
            findMerchantsInteractor.findMerchants { ::onMerchantsLoaded }
        }
        return _merchantsState
    }

    private fun onMerchantsLoaded(merchantsResponse: MerchantsResponse) {
        _merchantsState.value = ScreenState.Render(MerchantsState.ShowMerchants(merchantsResponse))
    }

    fun onMerchantClicked(item: String) {
        _merchantsState.value = ScreenState.Render(MerchantsState.ShowMessage(item))
    }

    class MerchantsViewModelFactory(private val findMerchantsInteractor: FindMerchantsInteractor) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MerchantsViewModel(findMerchantsInteractor) as T
        }
    }

}