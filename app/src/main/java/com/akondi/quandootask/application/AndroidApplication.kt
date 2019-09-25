package com.akondi.quandootask.application

import android.app.Application
import com.akondi.quandootask.core.di.application.ApplicationComponent
import com.akondi.quandootask.core.di.application.ApplicationModule
import com.akondi.quandootask.core.di.application.DaggerApplicationComponent


class  AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}