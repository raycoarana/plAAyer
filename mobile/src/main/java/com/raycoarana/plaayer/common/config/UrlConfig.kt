package com.raycoarana.plaayer.common.config

import com.raycoarana.awex.Awex
import com.raycoarana.plaayer.core.SimplePromise
import javax.inject.Inject

class UrlConfig @Inject constructor(
        private val awex: Awex
) {
    fun getChannelsUrl(): SimplePromise<String> = awex.of(DEFAULT_CHANNEL_URL)
    fun getEpgUrl(): SimplePromise<String> = awex.of(DEFAULT_EPG_URL)

    companion object {
        const val DEFAULT_CHANNEL_URL = "http://91.121.64.179/tdt_project/output/channels.json"
        const val DEFAULT_EPG_URL = "https://raw.githubusercontent.com/HelmerLuzo/TDTChannels_EPG/master/TDTChannels_EPG.xml.gz"
    }
}