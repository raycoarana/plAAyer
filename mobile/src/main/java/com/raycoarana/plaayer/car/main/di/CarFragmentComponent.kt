package com.raycoarana.plaayer.car.main.di

import android.databinding.DataBindingComponent
import com.raycoarana.plaayer.car.tv.view.LiveTvChannelGridFragment
import com.raycoarana.plaayer.car.main.view.MainFragment
import com.raycoarana.plaayer.car.player.PlayerFragment
import com.raycoarana.plaayer.core.di.CarFragmentModule
import com.raycoarana.plaayer.core.di.FragmentSingleton
import dagger.Subcomponent

@FragmentSingleton
@Subcomponent(modules = [CarFragmentModule::class])
interface CarFragmentComponent : DataBindingComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: LiveTvChannelGridFragment)
    fun inject(fragment: PlayerFragment)
}