package com.raycoarana.plaayer.common

import android.content.Context

fun fromAppComponentOf(context: Context): PlaayerApplicationComponent =
    (context.applicationContext as PlaayerApplication).getApplicationComponent()
