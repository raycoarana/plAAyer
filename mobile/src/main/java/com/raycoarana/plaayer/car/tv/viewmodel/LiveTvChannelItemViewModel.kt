package com.raycoarana.plaayer.car.tv.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.main.domain.AppSection
import com.raycoarana.plaayer.car.player.PlayerFragment
import com.raycoarana.plaayer.core.ui.ItemViewModel

class LiveTvChannelItemViewModel constructor(
        private val navigator: Navigator,
        val title: String,
        val url: String,
        val logo: Int = 0
) : ItemViewModel(R.layout.item_live_tv_channel) {
    fun open() {
        navigator.navigateToPlayer(title, url, PlayerFragment.UrlType.HLS)
    }
}
