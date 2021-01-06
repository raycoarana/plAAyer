package com.raycoarana.plaayer.common.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.raycoarana.plaayer.car.main.di.CarFragmentComponent
import com.raycoarana.plaayer.car.main.view.CarActivity
import com.raycoarana.plaayer.core.di.CarFragmentModule

fun fromAppComponentOf(context: Context): PlaayerApplicationComponent =
    (context.applicationContext as PlaayerApplication).getApplicationComponent()

fun fromCarActivityComponentOf(context: Context?, fragment: Fragment): CarFragmentComponent =
    (context as CarActivity).carActivityComponent.plus(CarFragmentModule(fragment))
