package com.akondi.quandootask.features.merchants_feature

import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.entities.merchantdetails.MerchantDetails
import com.akondi.quandootask.features.merchants_feature.GetMerchantDetails.Params

import javax.inject.Inject

class GetMerchantDetails
@Inject constructor(private val merchantsRepository: MerchantsRepository) : UseCase<MerchantDetails, Params>() {

    override suspend fun run(params: Params) = merchantsRepository.merchantDetails(params.id)

    data class Params(val id: Int)
}