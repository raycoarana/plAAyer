package com.raycoarana.plaayer.car.media.domain.model

import android.graphics.Bitmap

open class MediaItem constructor(
        val title: String,
        val path: String,
        val type: Type,
        val thumbnail: Bitmap
) {

    enum class Type {
        VIDEO_ITEM,
        PHOTO_ITEM,
        FOLDER
    }
}