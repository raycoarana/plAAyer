package com.raycoarana.plaayer.core

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.RawRes
import android.support.annotation.StringRes
import com.raycoarana.plaayer.BuildConfig
import com.raycoarana.plaayer.core.di.ActivitySingleton
import com.raycoarana.plaayer.core.di.ApplicationContext
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.Charset
import javax.inject.Inject

@ActivitySingleton
class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(@StringRes resId: Int) = context.getString(resId)
    fun getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, formatArgs)

    fun getReaderFromRaw(@RawRes resId: Int) : Reader =
        InputStreamReader(context.resources.openRawResource(resId), Charset.forName("UTF-8"))

    @DrawableRes
    fun getDrawableResId(resourceName: String): Int =
        context.resources.getIdentifier(resourceName, "drawable", BuildConfig.APPLICATION_ID)
}
