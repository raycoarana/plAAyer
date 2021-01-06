package com.raycoarana.plaayer.common.tv.network

import com.raycoarana.awex.Awex
import com.raycoarana.plaayer.common.config.UrlConfig
import com.raycoarana.plaayer.common.tv.network.reader.ChannelListDtoReader
import com.raycoarana.plaayer.common.tv.network.reader.ChannelListDtoReaderFactory
import com.raycoarana.plaayer.common.tv.network.reader.EpgDtoReader
import com.raycoarana.plaayer.common.tv.network.reader.EpgDtoReaderFactory
import com.raycoarana.plaayer.core.SimplePromise
import com.raycoarana.plaayer.core.filterNullable
import com.raycoarana.plaayer.core.newCallWithPromise
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*
import javax.inject.Inject

class ChannelsNetworkDataSource @Inject constructor(
        private val awex: Awex,
        private val urlConfig: UrlConfig,
        private val epgDtoReaderFactory: EpgDtoReaderFactory,
        private val channelListDtoReaderFactory: ChannelListDtoReaderFactory
) {

    private val okHttpClient = OkHttpClient.Builder().build()

    fun requestChannelList(): SimplePromise<ChannelListDtoReader> =
        urlConfig.getChannelsUrl()
                .mapSingle { channelsUrl -> Request.Builder().url(channelsUrl).get().build() }
                .then { request -> okHttpClient.newCallWithPromise(awex, request) }
                .mapSingle { response -> response.body }
                .filterNullable()
                .mapSingle { body -> channelListDtoReaderFactory.create(body.byteStream()) }

    fun requestEpg(): SimplePromise<EpgDtoReader> =
        urlConfig.getEpgUrl()
                .mapSingle { epgUrl -> Request.Builder().url(epgUrl).get().build() }
                .then { request -> okHttpClient.newCallWithPromise(awex, request) }
                .mapSingle { response -> response.body }
                .filterNullable()
                .mapSingle { body -> epgDtoReaderFactory.create(body.byteStream()) }

    fun checkEpgLastUpdate(): SimplePromise<Date> {
        val content = "{\n" +
                "  repository(owner: \"HelmerLuzo\", name: \"TDTChannels_EPG\") {\n" +
                "    object(expression: \"master\") {\n" +
                "      ... on Commit {\n" +
                "        history(path: \"TDTChannels_EPG.xml.gz\", first: 1) {\n" +
                "          nodes {\n" +
                "            committedDate\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n"

        val request = Request.Builder()
                .url("https://api.github.com/graphql")
                .post(content.toByteArray().toRequestBody("application/json".toMediaType()))
                .build()
        return okHttpClient.newCallWithPromise(awex, request).mapSingle {
            Date() // TODO
        }
    }
}
