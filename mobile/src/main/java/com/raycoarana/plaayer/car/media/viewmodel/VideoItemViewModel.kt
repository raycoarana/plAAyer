package com.raycoarana.plaayer.car.media.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.media.domain.model.MediaItem
import com.raycoarana.plaayer.car.player.PlayerFragment

class VideoItemViewModel constructor(
        private val navigator: Navigator,
        private val mediaItem: MediaItem,
        private val parentPath: String?
) : FileItemViewModel(R.layout.item_media_video, mediaItem.title, mediaItem.thumbnail) {

    override fun open() {
        if (isFolder) {
            navigator.navigateToVideoFolder(mediaItem.path, parentPath)
        } else {
            navigator.navigateToPlayer(mediaItem.title, "file://" + mediaItem.path, PlayerFragment.UrlType.FILE)
        }
    }

    override val isFolder: Boolean
        get() = mediaItem.type == MediaItem.Type.FOLDER

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VideoItemViewModel

        if (mediaItem.path != other.mediaItem.path) return false

        return true
    }

    override fun hashCode(): Int {
        return mediaItem.path.hashCode()
    }

}
