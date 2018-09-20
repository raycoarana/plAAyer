package com.raycoarana.plaayer.car.media.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.media.domain.model.MediaItem

class PhotoItemViewModel constructor(
        private val navigator: Navigator,
        private val mediaItem: MediaItem,
        private val parentPath: String?
) : FileItemViewModel(R.layout.item_media_photo, mediaItem.title, mediaItem.thumbnail) {

    override fun open() {
        if (isFolder) {
            navigator.navigateToPhotoFolder(mediaItem.path, parentPath)
        } else {
            navigator.navigateToViewer("file://" + mediaItem.path)
        }
    }

    override val isFolder: Boolean
        get() = mediaItem.type == MediaItem.Type.FOLDER

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotoItemViewModel

        if (mediaItem.path != other.mediaItem.path) return false

        return true
    }

    override fun hashCode(): Int {
        return mediaItem.path.hashCode()
    }

}
