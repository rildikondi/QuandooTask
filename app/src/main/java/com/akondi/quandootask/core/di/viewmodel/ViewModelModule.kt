package com.akondi.quandootask.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akondi.quandootask.features.merchants_feature.MerchantsViewModel
import com.akondi.quandootask.features.merchants_feature.MerchantDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MerchantsViewModel::class)
    abstract fun bindsMoviesViewModel(merchantsViewModel: MerchantsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MerchantDetailsViewModel::class)
    abstract fun bindsMovieDetailsViewModel(merchantDetailsViewModel: MerchantDetailsViewModel): ViewModel
}