package com.raycoarana.plaayer.car.media.viewmodel

import android.databinding.ObservableField
import android.util.Log
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.media.domain.MediaRepository
import com.raycoarana.plaayer.car.media.domain.model.MediaItem
import javax.inject.Inject

class VideoGridViewModel @Inject constructor(
        private val navigator: Navigator,
        private val mediaRepository: MediaRepository
) {
    val items: ObservableField<List<FileItemViewModel>> = ObservableField(emptyList())

    lateinit var path: String

    fun init(mediaItemType: MediaItem.Type, path: String, parentPath: String?) {
        this.path = path
        val promise = when (mediaItemType) {
            MediaItem.Type.VIDEO_ITEM -> mediaRepository.listVideos(path)
            else -> mediaRepository.listPhotos(path)
        }
        promise.done { mediaItems ->
            val parentItem : List<FileItemViewModel> = if (parentPath != null) {
                listOf(ParentItemViewModel(mediaItemType, navigator))
            } else {
                emptyList()
            }
            items.set(parentItem.union(mediaItems.map {
                when (mediaItemType) {
                    MediaItem.Type.VIDEO_ITEM -> VideoItemViewModel(navigator, it, path)
                    else -> PhotoItemViewModel(navigator, it, path)
                }
            }).toList())
        }.fail {
            Log.e("FAIL", it.message, it)
        }
    }
}
