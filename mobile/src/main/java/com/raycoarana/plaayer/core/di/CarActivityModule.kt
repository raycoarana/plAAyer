package com.raycoarana.plaayer.core.di

import android.content.Context
import android.support.car.Car
import android.support.car.CarAppFocusManager
import android.support.car.CarConnectionCallback
import android.support.car.CarInfoManager
import android.support.car.hardware.CarSensorManager
import android.support.car.media.CarAudioManager
import android.util.Log
import com.google.android.apps.auto.sdk.CarActivity
import dagger.Module
import dagger.Provides

@Module
class CarActivityModule constructor(private val activity: CarActivity) {

    private val car: Car
    private lateinit var carAppFocusManager: CarAppFocusManager
    private lateinit var carAudioManager: CarAudioManager
    private lateinit var carInfoManager: CarInfoManager
    private lateinit var carSensorManager: CarSensorManager

    init {
        car = Car.createCar(activity, object : CarConnectionCallback() {
            override fun onConnected(car: Car) {
                Log.d("CAR", "onConnected")

                carAppFocusManager = car.getCarManager(CarAppFocusManager::class.java)
                carAudioManager = car.getCarManager(CarAudioManager::class.java)
                carInfoManager = car.getCarManager(CarInfoManager::class.java)
                carSensorManager = car.getCarManager(CarSensorManager::class.java)
            }

            override fun onDisconnected(car: Car) {
                Log.d("CAR", "onDisconnected")
            }
        })
        car.connect()
    }

    @Provides
    @CarActivityContext
    fun provideContext() : Context = activity

    @Provides
    @ActivitySingleton
    fun provideCarAppFocusManager() : CarAppFocusManager = carAppFocusManager

    @Provides
    @ActivitySingleton
    fun provideCarAudioManager() : CarAudioManager = carAudioManager

    @Provides
    @ActivitySingleton
    fun provideCarInfoManager() : CarInfoManager = carInfoManager

    @Provides
    @ActivitySingleton
    fun provideCarSensorManager() : CarSensorManager = carSensorManager
}
