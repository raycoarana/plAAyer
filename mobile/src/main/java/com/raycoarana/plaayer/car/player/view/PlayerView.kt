package com.raycoarana.plaayer.car.player.view

interface PlayerView {
    fun initializePlayer(urlType: PlayerFragment.UrlType, url: String)
    fun releasePlayer()
    fun setStreams(currentStreamLabel: String, allStreamLabels: List<String>)
}
