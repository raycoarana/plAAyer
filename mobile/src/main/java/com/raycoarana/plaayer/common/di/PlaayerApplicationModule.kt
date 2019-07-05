package com.raycoarana.plaayer.common.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.raycoarana.awex.Awex
import com.raycoarana.awex.android.AndroidLogger
import com.raycoarana.awex.android.AndroidUIThread
import com.raycoarana.awex.policy.LinearWithRealTimePriority
import com.raycoarana.plaayer.core.di.ApplicationContext
import com.raycoarana.plaayer.core.json.Json
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlaayerApplicationModule constructor(private val application: Application) {
    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideAwex(): Awex = Awex(
            AndroidUIThread(),
            AndroidLogger(),
            LinearWithRealTimePriority()
    )

    @Provides
    @Singleton
    fun provideJson(): Json = Json(Gson())
}
