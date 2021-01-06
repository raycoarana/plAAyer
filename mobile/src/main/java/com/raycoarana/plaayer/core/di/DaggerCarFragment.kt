package com.raycoarana.plaayer.core.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.raycoarana.plaayer.car.main.di.CarFragmentComponent
import com.raycoarana.plaayer.common.di.fromCarActivityComponentOf

abstract class DaggerCarFragment : Fragment() {

    protected lateinit var component : CarFragmentComponent
    private var alreadyInjected = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (!alreadyInjected) {
            component = fromCarActivityComponentOf(context, this)
            alreadyInjected = true
            onComponentReady()
        }
    }

    abstract fun onComponentReady()
}
