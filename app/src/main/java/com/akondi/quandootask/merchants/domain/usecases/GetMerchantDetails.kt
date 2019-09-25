package com.akondi.quandootask.merchants.domain.usecases

import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.merchants.domain.entities.merchantdetails.MerchantDetails
import com.akondi.quandootask.merchants.domain.usecases.GetMerchantDetails.Params
import com.akondi.quandootask.merchants.domain.repository.MerchantsRepository

import javax.inject.Inject

class GetMerchantDetails
@Inject constructor(private val merchantsRepository: MerchantsRepository) : UseCase<MerchantDetails, Params>() {

    override suspend fun run(params: Params) = merchantsRepository.merchantDetails(params.id)

    data class Params(val id: Int)
}