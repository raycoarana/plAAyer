package com.raycoarana.plaayer.common.tv.network

import com.raycoarana.awex.Awex
import java.io.InputStream
import javax.inject.Inject

class EpgDtoReaderFactory @Inject constructor(
        private val awex: Awex
) {
    fun create(inputStream: InputStream) = EpgDtoReader(awex, inputStream)
}
