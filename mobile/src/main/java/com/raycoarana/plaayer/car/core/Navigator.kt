package com.raycoarana.plaayer.car.core

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.google.android.apps.auto.sdk.CarToast
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.main.domain.AppSection
import com.raycoarana.plaayer.car.main.view.CarActivity
import com.raycoarana.plaayer.car.main.view.MainFragment
import com.raycoarana.plaayer.car.media.domain.model.MediaItem
import com.raycoarana.plaayer.car.media.view.MediaGridFragment
import com.raycoarana.plaayer.car.media.view.PhotoViewerFragment
import com.raycoarana.plaayer.car.player.view.PlayerFragment
import com.raycoarana.plaayer.car.tv.view.LiveTvChannelGridFragment
import com.raycoarana.plaayer.core.di.CarActivityContext
import javax.inject.Inject

class Navigator @Inject constructor(@CarActivityContext private val context: Context) {

    fun navigateToPlayer(id: Int, title: String, urlType: PlayerFragment.UrlType) {
        val playerFragment = PlayerFragment()
        val arguments = Bundle()
        arguments.putInt(PlayerFragment.ARGS_DATA_ID, id)
        arguments.putString(PlayerFragment.ARGS_URL_TYPE, urlType.toString())
        playerFragment.arguments = arguments
        carUiController().statusBarController.setTitle(title)
        navigateTo(playerFragment, true)
    }

    fun navigateToPlayer(title: String, fileUri: String) {
        val playerFragment = PlayerFragment()
        val arguments = Bundle()
        arguments.putString(PlayerFragment.ARGS_DATA_URL, fileUri)
        arguments.putString(PlayerFragment.ARGS_URL_TYPE, PlayerFragment.UrlType.FILE.toString())
        playerFragment.arguments = arguments
        carUiController().statusBarController.setTitle(title)
        navigateTo(playerFragment, true)
    }

    fun navigateToViewer(photoUri: String) {
        val fragment = PhotoViewerFragment()
        val arguments = Bundle()
        arguments.putString(PhotoViewerFragment.ARGS_DATA_URL, photoUri)
        fragment.arguments = arguments
        navigateTo(fragment, true)
    }

    fun navigateToSection(title: String, appSection: AppSection) {
        val fragment = when(appSection) {
            AppSection.DASHBOARD -> MainFragment()
            AppSection.LIVE_TV -> LiveTvChannelGridFragment()
            AppSection.VIDEO -> MediaGridFragment.build(MediaItem.Type.VIDEO_ITEM)
            AppSection.GALLERY -> MediaGridFragment.build(MediaItem.Type.PHOTO_ITEM)
            else -> {
                CarToast.makeText(context, "Not implemented in this version", Toast.LENGTH_SHORT).show()
                return
            }
        }

        if (appSection != AppSection.DASHBOARD) {
            menuController().showMenuButton()
        } else {
            menuController().hideMenuButton()
        }
        carUiController().statusBarController.showAppHeader()
        carUiController().statusBarController.setTitle(title)
        navigateTo(fragment, false)
    }

    private fun navigateTo(fragment: Fragment, addToBackStack: Boolean = false) {
        val currentActivity = context as CarActivity
        val transaction = currentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    private fun carUiController() = (context as CarActivity).carUiController
    private fun menuController() = carUiController().menuController

    fun navigateToVideoFolder(path: String, parentPath: String? = null) {
        navigateTo(MediaGridFragment.build(MediaItem.Type.VIDEO_ITEM, path, parentPath), true)
    }

    fun navigateToPhotoFolder(path: String, parentPath: String? = null) {
        navigateTo(MediaGridFragment.build(MediaItem.Type.PHOTO_ITEM, path, parentPath), true)
    }

    fun navigateBack() {
        val currentActivity = context as CarActivity
        currentActivity.supportFragmentManager.popBackStack()
    }
}
