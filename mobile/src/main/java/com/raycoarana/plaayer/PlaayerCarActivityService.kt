package com.raycoarana.plaayer

import com.google.android.apps.auto.sdk.CarActivity
import com.google.android.apps.auto.sdk.CarActivityService

class PlaayerCarActivityService : CarActivityService() {
    override fun getCarActivity(): Class<out CarActivity> = PlaayerCarActivity::class.java
}
