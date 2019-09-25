package com.akondi.quandootask.merchants.domain.usecases

import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.merchants.domain.repository.MerchantsRepository
import com.akondi.quandootask.merchants.domain.entities.merchants.Merchants
import javax.inject.Inject

class GetMerchants
@Inject constructor(private val merchantsRepository: MerchantsRepository) : UseCase<Merchants, UseCase.None>() {

    override suspend fun run(params: None) = merchantsRepository.merchants()
}