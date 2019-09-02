package com.akondi.quandootask.features.merchants_feature

import android.content.Context
import com.akondi.quandootask.core.exception.Failure
import com.akondi.quandootask.core.functional.Either
import com.akondi.quandootask.core.functional.Either.Right
import com.akondi.quandootask.core.interactor.UseCase
import com.akondi.quandootask.core.navigation.Navigator
import javax.inject.Inject

class PlayMerchant
@Inject constructor(private val context: Context,
                    private val navigator: Navigator
) : UseCase<UseCase.None, PlayMerchant.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}