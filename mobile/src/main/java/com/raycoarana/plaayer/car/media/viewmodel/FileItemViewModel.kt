package com.raycoarana.plaayer.car.media.viewmodel

import android.graphics.Bitmap
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.core.ui.ItemViewModel

abstract class FileItemViewModel constructor(
        layoutResId: Int,
        val title: String,
        val thumbnail: Bitmap?
) :  ItemViewModel(layoutResId) {
    abstract fun open()
    abstract val isFolder: Boolean
}
