package com.raycoarana.plaayer.car.tv.viewmodel

import android.databinding.ObservableField
import com.raycoarana.awex.Promise
import com.raycoarana.awex.callbacks.UIDoneCallback
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.tv.domain.ChannelRepository
import com.raycoarana.plaayer.car.tv.domain.model.Channel
import com.raycoarana.plaayer.core.uiDone
import javax.inject.Inject

class LiveTvChannelGridViewModel @Inject constructor(
        navigator: Navigator,
        channelRepository: ChannelRepository
) {
    val items: ObservableField<List<LiveTvChannelItemViewModel>> = ObservableField(emptyList())

    init {
        channelRepository.getChannelList()
                .uiDone(UIDoneCallback {
                    items.set(it.map { LiveTvChannelItemViewModel(navigator, it) })
                })
    }
}
