package com.akondi.quandootask.core.di.application

import com.akondi.quandootask.application.AndroidApplication
import com.akondi.quandootask.core.di.viewmodel.ViewModelModule
import com.akondi.quandootask.core.navigation.RouteActivity
import com.akondi.quandootask.merchants.presentation.fragments.MerchantDetailsFragment
import com.akondi.quandootask.merchants.presentation.fragments.MerchantsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(merchantsFragment: MerchantsFragment)
    fun inject(merchantDetailsFragment: MerchantDetailsFragment)
}