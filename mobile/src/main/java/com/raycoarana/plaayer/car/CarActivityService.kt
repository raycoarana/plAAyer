package com.raycoarana.plaayer.car

import com.google.android.apps.auto.sdk.CarActivityService
import com.raycoarana.plaayer.car.main.view.CarActivity

class CarActivityService : CarActivityService() {
    override fun getCarActivity(): Class<out com.google.android.apps.auto.sdk.CarActivity> = CarActivity::class.java
}
