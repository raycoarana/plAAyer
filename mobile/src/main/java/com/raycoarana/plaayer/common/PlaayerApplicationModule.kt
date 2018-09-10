package com.raycoarana.plaayer.common

import android.app.Application
import android.content.Context
import com.raycoarana.plaayer.core.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class PlaayerApplicationModule constructor(private val application: Application) {
    @Provides
    @ApplicationContext
    fun provideContext() : Context = application
}