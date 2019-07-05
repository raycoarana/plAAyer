package com.raycoarana.plaayer.common.tv.network

import com.fasterxml.jackson.core.JsonFactory
import java.io.InputStream
import javax.inject.Inject

class ChannelListDtoReaderFactory @Inject constructor(
        private val jsonFactory: JsonFactory
) {
    fun create(inputStream: InputStream): ChannelListDtoReader = ChannelListDtoReader(jsonFactory, inputStream)
}