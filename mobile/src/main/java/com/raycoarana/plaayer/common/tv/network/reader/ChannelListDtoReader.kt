package com.raycoarana.plaayer.common.tv.network.reader

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.raycoarana.plaayer.common.tv.network.model.Channel
import com.raycoarana.plaayer.common.tv.network.model.Option
import java.io.Closeable
import java.io.InputStream
import javax.inject.Inject

class ChannelListDtoReader @Inject constructor(
        jsonFactory: JsonFactory,
        inputStream: InputStream
) : Closeable {
    private var currentState: State? = null
    private var currentAmbit: String? = null
    private var currentCountry: String? = null
    private var updated: Long? = null
    private val parser = jsonFactory.createParser(inputStream)

    fun getUpdatedTimestamp(): Long? {
        return if (updated != null) {
            updated
        } else if (parser.currentName == UPDATED) {
            updated = parser.nextLongValue(-1)
            updated
        } else {
            null
        }
    }

    fun nextChannel(): Channel? {
        if (currentState == null) {
            readHeaderUntilCountriesArray()
        }

        var channel: Channel? = null
        loop@ while (channel == null) {
            channel = when (currentState) {
                State.IN_COUNTRIES_ARRAY -> readCountry()
                State.IN_AMBITS_ARRAY -> readAmbit()
                State.IN_CHANNELS_ARRAY -> readChannel()
                State.END_CHANNELS_ARRAY -> {
                    assert(parser.nextToken() == JsonToken.END_OBJECT)
                    currentState = State.IN_AMBITS_ARRAY
                    null
                }
                State.END_AMBITS_ARRAY -> {
                    assert(parser.nextToken() == JsonToken.END_OBJECT)
                    currentState = State.IN_COUNTRIES_ARRAY
                    null
                }
                State.END_COUNTRIES_ARRAY -> {
                    parser.nextToken()
                    currentState = State.ROOT_OBJECT
                    null
                }
                else -> break@loop
            }
        }
        return channel
    }

    private fun readCountry(): Channel? {
        if (parser.nextToken() == JsonToken.END_ARRAY) {
            currentState = State.END_COUNTRIES_ARRAY
            return null
        }

        assert(parser.currentToken == JsonToken.START_OBJECT) { parser.currentLocation.toString() + " " + parser.currentToken }
        if (parser.nextToken() == JsonToken.FIELD_NAME && parser.currentName == NAME) {
            currentCountry = parser.nextTextValue()
        }
        parser.consumeUntilFieldValueOf(AMBITS)
        assert(parser.nextToken() == JsonToken.START_ARRAY)

        currentState = State.IN_AMBITS_ARRAY
        return null
    }

    private fun readAmbit(): Channel? {
        if (parser.nextToken() == JsonToken.END_ARRAY) {
            currentState = State.END_AMBITS_ARRAY
            return null
        }
        assert(parser.currentToken == JsonToken.START_OBJECT) { parser.currentLocation.toString() + " " + parser.currentToken }

        if (parser.nextToken() == JsonToken.FIELD_NAME && parser.currentName == NAME) {
            currentAmbit = parser.nextTextValue()
        }
        parser.consumeUntilFieldValueOf(CHANNELS)
        assert(parser.nextToken() == JsonToken.START_ARRAY)

        currentState = State.IN_CHANNELS_ARRAY
        return null
    }

    private fun readChannel(): Channel? {
        if (parser.currentToken != JsonToken.START_OBJECT) {
            if (parser.nextToken() == JsonToken.END_ARRAY) {
                return null
            }

            assert(parser.currentToken == JsonToken.START_OBJECT) { parser.currentLocation.toString() + " " + parser.currentToken }
        }

        val channelBuilder = Channel.Builder()
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            when (parser.currentName()) {
                NAME -> channelBuilder.withName(parser.nextTextValue())
                WEB -> channelBuilder.withWeb(parser.nextTextValue())
                LOGO -> channelBuilder.withLogo(parser.nextTextValue())
                RESOLUTION -> channelBuilder.withResolution(parser.nextTextValue())
                EPG_ID -> channelBuilder.withEpgId(parser.nextTextValue())
                OPTIONS -> channelBuilder.withOptions(readOptions())
                EXTRA_INFO -> channelBuilder.withExtraInfo(parser.nextStringArray())
            }
        }

        channelBuilder.withCategory(currentAmbit)
        channelBuilder.withCountry(currentCountry)
        if (parser.nextToken() == JsonToken.END_ARRAY) {
            currentState = State.END_CHANNELS_ARRAY
        }
        return channelBuilder.build()
    }

    private fun readOptions(): List<Option> {
        assert(parser.nextToken() == JsonToken.START_ARRAY)

        val items = ArrayList<Option>()
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            val optionBuilder = Option.Builder()
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                when (parser.currentName()) {
                    FORMAT -> optionBuilder.withFormat(parser.nextTextValue())
                    URL -> optionBuilder.withUrl(parser.nextTextValue())
                    EXTRA_INFO -> optionBuilder.withExtraInfo(parser.nextStringArray())
                }
            }
            items.add(optionBuilder.build())
        }
        return items
    }

    private fun readHeaderUntilCountriesArray() {
        loop@ while (parser.nextToken() != JsonToken.FIELD_NAME) {
            when (parser.currentName) {
                COUNTRIES -> break@loop
                UPDATED -> updated = parser.longValue
            }
        }

        while (parser.nextToken() != JsonToken.START_ARRAY) {

        }
        currentState = State.IN_COUNTRIES_ARRAY
    }

    override fun close() {
        parser.close()
    }

    private fun JsonParser.consumeUntilFieldValueOf(fieldName: String) = apply {
        while (parser.currentToken != JsonToken.FIELD_NAME) {
            if (parser.currentName == fieldName) {
                break
            }
            parser.nextToken()
        }
    }

    private fun JsonParser.nextStringArray(): ArrayList<String> {
        assert(this.nextToken() == JsonToken.START_ARRAY)

        val items = ArrayList<String>()
        while (this.nextToken() == JsonToken.VALUE_STRING) {
            items.add(this.valueAsString)
        }
        assert(this.currentToken() == JsonToken.END_ARRAY)

        return items
    }

    enum class State {
        IN_COUNTRIES_ARRAY,
        IN_AMBITS_ARRAY,
        IN_CHANNELS_ARRAY,
        END_CHANNELS_ARRAY,
        END_AMBITS_ARRAY,
        END_COUNTRIES_ARRAY,
        ROOT_OBJECT
    }

    companion object {
        private const val COUNTRIES = "countries"
        private const val AMBITS = "ambits"
        private const val CHANNELS = "channels"
        private const val UPDATED = "updated"
        private const val NAME = "name"
        private const val WEB = "web"
        private const val LOGO = "logo"
        private const val RESOLUTION = "resolution"
        private const val EPG_ID = "epg_id"
        private const val OPTIONS = "options"
        private const val EXTRA_INFO = "extra_info"
        private const val FORMAT = "format"
        private const val URL = "url"
    }
}

