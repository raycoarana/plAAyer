package com.raycoarana.plaayer.common

import com.raycoarana.plaayer.core.di.ActivityModule
import com.raycoarana.plaayer.smartphone.installer.InstallerActivityComponent
import dagger.Component

@Component(modules = [PlaayerApplicationModule::class])
interface PlaayerApplicationComponent {
    fun inject(application: PlaayerApplication)
    fun plus(module: ActivityModule): InstallerActivityComponent
}
