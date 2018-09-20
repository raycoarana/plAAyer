package com.raycoarana.plaayer.car.media.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import com.raycoarana.plaayer.BuildConfig
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.core.di.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class MediaStorage @Inject constructor(
        @ApplicationContext private val context: Context
) {
    fun rootDirs(externalFileType: String): Array<File> =
            ContextCompat.getExternalFilesDirs(context, externalFileType)
                    .map { File(it.absolutePath.replace("Android/data/${BuildConfig.APPLICATION_ID}/files", "")) }
                    .toTypedArray()

    fun createVideoThumbnail(file: File): Bitmap =
            createThumbnail(file) {
                ThumbnailUtils.createVideoThumbnail(it.absolutePath, MediaStore.Video.Thumbnails.MINI_KIND)
            }

    fun createPhotoThumbnail(file: File): Bitmap =
            createThumbnail(file) {
                ThumbnailUtils.extractThumbnail(
                        BitmapFactory.decodeFile(file.absolutePath),
                        THUMBNAIL_SIZE,
                        THUMBNAIL_SIZE)
            }

    private fun createThumbnail(file: File, createThumbnail: (File) -> Bitmap): Bitmap {
        val thumbnailFile = File(file.parentFile, "." + file.name + ".thb")
        return if (thumbnailFile.exists()) {
            BitmapFactory.decodeFile(thumbnailFile.absolutePath)
        } else {
            val bitmap = createThumbnail(file)
            val outputStream = FileOutputStream(thumbnailFile)
            outputStream.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            bitmap
        }
    }

    fun createFolderThumbnail(file: File): Bitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.icn_folder)

    companion object {
        const val THUMBNAIL_SIZE = 512
    }
}
