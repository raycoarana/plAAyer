package com.raycoarana.plaayer.smartphone.installer.di

import com.raycoarana.plaayer.core.di.ActivityModule
import com.raycoarana.plaayer.smartphone.installer.view.InstallerActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, InstallerActivityModule::class])
interface InstallerActivityComponent {
    fun inject(activity: InstallerActivity)
}