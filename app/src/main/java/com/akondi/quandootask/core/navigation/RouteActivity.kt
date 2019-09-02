package com.akondi.quandootask.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akondi.quandootask.application.AndroidApplication
import com.akondi.quandootask.core.di.application.ApplicationComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}