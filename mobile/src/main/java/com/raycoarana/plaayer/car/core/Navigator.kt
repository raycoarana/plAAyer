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
import com.raycoarana.plaayer.car.player.PlayerFragment
import com.raycoarana.plaayer.car.tv.view.LiveTvChannelGridFragment
import com.raycoarana.plaayer.core.di.CarActivityContext
import javax.inject.Inject

class Navigator @Inject constructor(@CarActivityContext private val context: Context) {

    fun navigateToPlayer(title: String, videoUri: String, urlType: PlayerFragment.UrlType) {
        val playerFragment = PlayerFragment()
        val arguments = Bundle()
        arguments.putString(PlayerFragment.ARGS_DATA_URL, videoUri)
        arguments.putString(PlayerFragment.ARGS_URL_TYPE, urlType.toString())
        playerFragment.arguments = arguments
        carUiController().statusBarController.setTitle(title)
        navigateTo(playerFragment, true)
    }

    fun navigateToSection(title: String, appSection: AppSection) {
        val fragment = when(appSection) {
            AppSection.DASHBOARD -> MainFragment()
            AppSection.LIVE_TV -> LiveTvChannelGridFragment()
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
        val transation = currentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)

        if (addToBackStack) {
            transation.addToBackStack(null)
        }

        transation.commit()
    }

    private fun carUiController() = (context as CarActivity).carUiController
    private fun menuController() = carUiController().menuController
}
