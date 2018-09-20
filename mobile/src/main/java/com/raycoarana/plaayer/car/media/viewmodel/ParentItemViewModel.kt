package com.raycoarana.plaayer.car.media.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.media.domain.model.MediaItem

open class ParentItemViewModel constructor(
        type: MediaItem.Type,
        private val navigator: Navigator
) : FileItemViewModel(if(type == MediaItem.Type.VIDEO_ITEM) R.layout.item_media_video else R.layout.item_media_photo, "..", null) {

    override val isFolder: Boolean
        get() = true

    override fun open() {
        navigator.navigateBack()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return true
    }
}
