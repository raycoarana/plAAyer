package com.raycoarana.plaayer.common.di

import com.raycoarana.plaayer.car.main.di.CarActivityComponent
import com.raycoarana.plaayer.core.di.ActivityModule
import com.raycoarana.plaayer.core.di.CarActivityModule
import com.raycoarana.plaayer.smartphone.installer.di.InstallerActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PlaayerApplicationModule::class])
interface PlaayerApplicationComponent {

    fun inject(application: PlaayerApplication)

    fun plus(module: ActivityModule): InstallerActivityComponent
    fun plus(module: CarActivityModule): CarActivityComponent

}
