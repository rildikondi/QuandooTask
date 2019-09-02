package com.akondi.quandootask.features.merchants_feature

import com.akondi.quandootask.core.exception.Failure
import com.akondi.quandootask.core.extensions.ConnectivityMode
import com.akondi.quandootask.core.functional.Either
import com.akondi.quandootask.core.platform.NetworkHandler
import com.akondi.quandootask.entities.merchantdetails.MerchantDetails
import com.akondi.quandootask.entities.merchantdetails.MerchantDetailsResponse
import com.akondi.quandootask.entities.merchants.Merchants
import com.akondi.quandootask.entities.merchants.MerchantsResponse
import retrofit2.Call
import javax.inject.Inject

interface MerchantsRepository {
    fun merchants(): Either<Failure, Merchants>
    fun merchantDetails(merchantId: Int): Either<Failure, MerchantDetails>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: MerchantsService) : MerchantsRepository {

        override fun merchants(): Either<Failure, Merchants> {
            return when (networkHandler.isConnected) {
                ConnectivityMode.NONE -> Either.Left(Failure.NetworkConnection)
                else -> request(service.merchants(), { it.toMerchants() }, MerchantsResponse.empty())
            }
        }

        override fun merchantDetails(merchantId: Int): Either<Failure, MerchantDetails> {
            return when (networkHandler.isConnected) {
                ConnectivityMode.NONE -> Either.Left(Failure.NetworkConnection)
                else -> request(service.merchantDetails(merchantId), {it.toMerchantDetails()}, MerchantDetailsResponse.empty())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}