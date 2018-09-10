package com.raycoarana.plaayer.core.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(private val activity: Activity) {
    @Provides
    @ActivityContext
    fun provideContext() : Context = activity
}
