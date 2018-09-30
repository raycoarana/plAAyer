package com.raycoarana.plaayer.car.tv.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.player.view.PlayerFragment
import com.raycoarana.plaayer.car.tv.domain.model.Channel
import com.raycoarana.plaayer.core.ui.ItemViewModel

class LiveTvChannelItemViewModel constructor(
        private val navigator: Navigator,
        private val channel: Channel
) : ItemViewModel(R.layout.item_live_tv_channel) {
    val title = channel.title
    val logo = channel.logo

    fun open() {
        navigator.navigateToPlayer(channel.id, title, PlayerFragment.UrlType.HLS)
    }
}
