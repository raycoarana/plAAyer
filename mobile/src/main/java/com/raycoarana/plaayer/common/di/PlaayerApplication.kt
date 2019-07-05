package com.raycoarana.plaayer.common.di

import android.app.Application
import com.bugsnag.android.Bugsnag

class PlaayerApplication : Application() {

    private lateinit var applicationComponent: PlaayerApplicationComponent

    fun getApplicationComponent(): PlaayerApplicationComponent = applicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerPlaayerApplicationComponent.builder().plaayerApplicationModule(PlaayerApplicationModule(this)).build()
        Bugsnag.init(this)
    }
}