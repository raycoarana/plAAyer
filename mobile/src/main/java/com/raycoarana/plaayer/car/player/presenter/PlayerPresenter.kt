package com.raycoarana.plaayer.car.player.presenter

import com.raycoarana.awex.Promise
import com.raycoarana.awex.callbacks.UIDoneCallback
import com.raycoarana.plaayer.car.player.view.PlayerFragment
import com.raycoarana.plaayer.car.player.view.PlayerView
import com.raycoarana.plaayer.car.tv.domain.ChannelRepository
import com.raycoarana.plaayer.car.tv.domain.model.Channel
import com.raycoarana.plaayer.car.tv.domain.model.Stream
import com.raycoarana.plaayer.core.uiDone
import javax.inject.Inject

class PlayerPresenter @Inject constructor(
        private val channelRepository: ChannelRepository
) {

    private lateinit var view: PlayerView
    private lateinit var urlType: PlayerFragment.UrlType
    private var staticUrl: String? = null
    private var currentStream: Stream? = null
    private lateinit var channelPromise: Promise<Channel, Void>

    fun initialize(view: PlayerView, channelId: Int?, staticUrl: String?, rawUrlType: String?) {
        this.view = view

        this.urlType = PlayerFragment.UrlType.values()
                .firstOrNull { it.toString() == rawUrlType } ?: PlayerFragment.UrlType.FILE

        when {
            staticUrl != null -> initializeStaticUrl(staticUrl)
            channelId != null -> initializeChannel(channelId)
            else -> throw Exception("No url or channel id")
        }
    }

    private fun initializeStaticUrl(url: String) {
        this.staticUrl = url
    }

    private fun initializeChannel(channelId: Int) {
        channelPromise = channelRepository.getChannel(channelId)
        channelPromise.uiDone(UIDoneCallback { channel ->
            val firstStream = channel.streams.first()
            currentStream = firstStream
            view.setStreams(firstStream.label, channel.streams.map { stream -> stream.label })
        })
    }

    fun onReady() {
        val staticUrl = this.staticUrl
        if (staticUrl != null) {
            view.initializePlayer(urlType, staticUrl)
        } else {
            channelPromise.uiDone(UIDoneCallback { channel ->
                currentStream?.let {
                    view.initializePlayer(urlType, it.url)
                }
            })
        }
    }

    fun onChangeStream(label: String) {
        channelPromise.uiDone(UIDoneCallback { channel ->
            channel.streams.firstOrNull { it.label == label }
                    ?.let { stream ->
                        view.releasePlayer()
                        view.initializePlayer(urlType, stream.url)
                    }
        })
    }

    fun onPause() {
        view.releasePlayer()
    }
}
