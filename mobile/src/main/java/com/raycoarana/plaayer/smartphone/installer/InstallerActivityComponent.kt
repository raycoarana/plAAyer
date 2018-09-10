package com.raycoarana.plaayer.smartphone.installer

import com.raycoarana.plaayer.core.di.ActivityModule
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, InstallerActivityModule::class])
interface InstallerActivityComponent {
    fun inject(activity: InstallerActivity)
}