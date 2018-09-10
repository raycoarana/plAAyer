package com.raycoarana.plaayer.core.ui

import android.content.Context
import android.widget.Toast
import com.raycoarana.plaayer.core.di.ActivityContext
import javax.inject.Inject

class FeedbackFactory @Inject constructor(
        @ActivityContext private val context: Context
) {
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
