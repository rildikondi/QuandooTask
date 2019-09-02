package com.akondi.quandootask.features.merchants_feature

import android.os.Bundle
import android.view.View
import com.akondi.quandootask.R
import com.akondi.quandootask.core.exception.Failure
import com.akondi.quandootask.core.extensions.*
import com.akondi.quandootask.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_merchant_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MerchantDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MERCHANT = "param_merchant"

        fun forMerchant(merchant: MerchantView): MerchantDetailsFragment {
            val merchantDetailsFragment = MerchantDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MERCHANT, merchant)
            merchantDetailsFragment.arguments = arguments

            return merchantDetailsFragment
        }
    }

    @Inject
    lateinit var merchantDetailsAnimator: MerchantDetailsAnimator

    private lateinit var merchantDetailsViewModel: MerchantDetailsViewModel

    override fun layoutId() = R.layout.fragment_merchant_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { merchantDetailsAnimator.postponeEnterTransition(it) }

        merchantDetailsViewModel = viewModel(viewModelFactory) {
            observe(merchantDetails, ::renderMerchantDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            merchantDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MERCHANT) as MerchantView).id)
        } else {
            merchantDetailsAnimator.scaleUpView(merchantPlay)
            merchantDetailsAnimator.cancelTransition(merchantPoster)
            merchantPoster.loadFromUrl((arguments!![PARAM_MERCHANT] as MerchantView).images[0].url)
        }
    }

    override fun onBackPressed() {
        merchantDetailsAnimator.fadeInvisible(scrollView, merchantDetails)
        if (merchantPlay.isVisible())
            merchantDetailsAnimator.scaleDownView(merchantPlay)
        else
            merchantDetailsAnimator.cancelTransition(merchantPoster)
    }

    private fun renderMerchantDetails(merchant: MerchantDetailsView?) {
        merchant?.let {
            with(merchant) {
                activity?.let {
                    merchantPoster.loadUrlAndPostponeEnterTransition(images[0].url, it)
                    it.toolbar.title = name
                }
                merchantAbout.text = if (bookable) "bookable online"  else " not bookable online"
                merchantRating.rating = reviewScore.toFloat()
                merchantAddress.text = location.address.street + ", " + location.address.number + ", " +
                        location.address.zipcode + ", " +  location.address.city
                merchantPhone.text = phoneNumber
                //merchantPlay.setOnClickListener { merchantDetailsViewModel.playMerchant(images[0].url) }
            }
        }
        merchantDetailsAnimator.fadeVisible(scrollView, merchantDetails)
        merchantDetailsAnimator.scaleUpView(merchantPlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is Failure.ServerError -> { notify(R.string.failure_server_error); close() }
            is MerchantFailure.NonExistentMerchant -> { notify(R.string.failure_merchant_non_existent); close() }
        }
    }
}