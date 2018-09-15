package com.raycoarana.plaayer.core.di

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raycoarana.plaayer.car.main.di.CarFragmentComponent
import com.raycoarana.plaayer.car.main.viewmodel.MainViewModel
import com.raycoarana.plaayer.common.fromCarActivityComponentOf
import com.raycoarana.plaayer.databinding.CarActivityMainBinding
import javax.inject.Inject

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
