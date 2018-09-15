package com.raycoarana.plaayer.core

import android.content.Context
import android.support.annotation.StringRes
import com.raycoarana.plaayer.core.di.ActivityContext
import com.raycoarana.plaayer.core.di.ActivitySingleton
import com.raycoarana.plaayer.core.di.CarActivityContext
import javax.inject.Inject

@ActivitySingleton
class CarResourceProvider @Inject constructor(@CarActivityContext private val context: Context) {
    fun getString(@StringRes resId: Int) = context.getString(resId)
    fun getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, formatArgs)
}