package com.raycoarana.plaayer.car.main.di

import com.raycoarana.plaayer.car.main.view.CarActivity
import com.raycoarana.plaayer.core.di.ActivitySingleton
import com.raycoarana.plaayer.core.di.CarActivityModule
import com.raycoarana.plaayer.core.di.CarFragmentModule
import dagger.Subcomponent
import javax.inject.Singleton

@ActivitySingleton
@Subcomponent(modules = [CarActivityModule::class])
interface CarActivityComponent {
    fun inject(carActivity: CarActivity)
    fun plus(carFragmentModule: CarFragmentModule): CarFragmentComponent
}