package com.akondi.quandootask.features.merchants_feature

import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.entities.merchants.Merchants
import javax.inject.Inject

class GetMerchants
@Inject constructor(private val merchantsRepository: MerchantsRepository) : UseCase<Merchants, UseCase.None>() {

    override suspend fun run(params: None) = merchantsRepository.merchants()
}