package com.raycoarana.plaayer.car.media.domain

import android.graphics.Bitmap
import android.os.Environment
import com.raycoarana.awex.Awex
import com.raycoarana.awex.Promise
import com.raycoarana.awex.Task
import com.raycoarana.plaayer.car.media.domain.model.MediaItem
import java.io.File
import javax.inject.Inject


class MediaRepository @Inject constructor(
        private val awex: Awex,
        private val mediaStorage: MediaStorage
) {
    fun listVideos(path: String = "/"): Promise<List<MediaItem>, Void> =
            awex.submit(object : Task<List<MediaItem>, Void>() {
                override fun run(): List<MediaItem> =
                        listFiles(path, Environment.DIRECTORY_MOVIES, SUPPORTED_VIDEO_EXTENSIONS)
                                .map { it.toMediaItem(MediaItem.Type.VIDEO_ITEM) }
            })

    fun listPhotos(path: String = "/"): Promise<List<MediaItem>, Void> =
            awex.submit(object : Task<List<MediaItem>, Void>() {
                override fun run(): List<MediaItem> =
                        listFiles(path, Environment.DIRECTORY_PICTURES, SUPPORTED_IMAGE_EXTENSIONS)
                                .union(listFiles(path, Environment.DIRECTORY_DCIM, SUPPORTED_IMAGE_EXTENSIONS))
                                .map { it.toMediaItem(MediaItem.Type.PHOTO_ITEM) }
            })

    fun listFiles(path: String, externalFileType: String, supportedTypes: Set<String>): List<File> {
        return if (path.isEmpty() || path.isBlank() || path == "/") {
            mediaStorage.rootDirs(externalFileType)
        } else {
            val dir = File(path)
            if (dir.exists() && dir.isDirectory) dir.listFiles() else emptyArray()
        }.asSequence().filterNotNull().filter {
            it.isDirectory || supportedTypes.contains(it.extension)
        }.toList()
    }

    private fun File.toMediaItem(type: MediaItem.Type): MediaItem {
        val itemType = if (this.isDirectory) {
            MediaItem.Type.FOLDER
        } else {
            type
        }
        val bitmap: Bitmap = when (itemType) {
            MediaItem.Type.VIDEO_ITEM -> mediaStorage.createVideoThumbnail(this)
            MediaItem.Type.PHOTO_ITEM -> mediaStorage.createPhotoThumbnail(this)
            else -> mediaStorage.createFolderThumbnail(this)
        }
        return MediaItem(nameWithoutExtension, this.absolutePath, itemType, bitmap)
    }

    companion object {
        val SUPPORTED_VIDEO_EXTENSIONS = setOf(
                "mp4",
                "mkv",
                "mov"
        )
        val SUPPORTED_IMAGE_EXTENSIONS = setOf(
                "jpg",
                "png",
                "gif"
        )
    }
}
