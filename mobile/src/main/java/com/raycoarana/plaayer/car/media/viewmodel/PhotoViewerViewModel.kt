package com.raycoarana.plaayer.car.media.viewmodel

import android.databinding.ObservableField
import android.net.Uri
import com.raycoarana.plaayer.car.core.Navigator
import javax.inject.Inject

class PhotoViewerViewModel @Inject constructor(
        private val navigator: Navigator
) {

    var uri = ObservableField<Uri>()

    fun initialize(imageUri: String) {
        uri.set(Uri.parse(imageUri))
    }

    fun close() {
        navigator.navigateBack()
    }
}