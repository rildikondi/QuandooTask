package com.akondi.quandootask.application

import android.app.Application
import com.akondi.quandootask.BuildConfig
import com.akondi.quandootask.core.di.application.ApplicationComponent
import com.akondi.quandootask.core.di.application.ApplicationModule
import com.akondi.quandootask.core.di.application.DaggerApplicationComponent
import leakcanary.AppWatcher


class AndroidApplication : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) AppWatcher.config.copy(watchFragmentViews = false)
    }
}