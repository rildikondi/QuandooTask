package com.akondi.quandootask.features.merchants_feature

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akondi.quandootask.R
import com.akondi.quandootask.core.exception.Failure
import com.akondi.quandootask.core.extensions.invisible
import com.akondi.quandootask.core.extensions.observe
import com.akondi.quandootask.core.extensions.failure
import com.akondi.quandootask.core.extensions.viewModel
import com.akondi.quandootask.core.extensions.visible
import com.akondi.quandootask.core.navigation.Navigator
import com.akondi.quandootask.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_merchants.*
import javax.inject.Inject

class MerchantsFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var merchantsAdapter: MerchantsAdapter

    private lateinit var merchantsViewModel: MerchantsViewModel

    override fun layoutId() = R.layout.fragment_merchants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        merchantsViewModel = viewModel(viewModelFactory) {
            observe(merchants, ::renderMerchants)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMerchants()
    }


    private fun initializeView() {
        merchantList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        merchantList.adapter = merchantsAdapter
        merchantsAdapter.clickListener = { merchant, navigationExtras ->
            navigator.showMerchantDetails(activity!!, merchant, navigationExtras) }
    }

    private fun loadMerchants() {
        emptyView.invisible()
        merchantList.visible()
        showProgress()
        merchantsViewModel.loadMerchants()
    }

    private fun renderMerchants(merchants: List<MerchantView>?) {
        merchantsAdapter.collection = merchants.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is MerchantFailure.MerchantsNotAvailable -> renderFailure(R.string.failure_merchants_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        merchantList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMerchants)
    }
}