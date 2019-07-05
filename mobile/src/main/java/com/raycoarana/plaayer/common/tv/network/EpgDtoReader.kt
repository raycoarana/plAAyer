package com.raycoarana.plaayer.common.tv.network

import com.raycoarana.awex.Awex
import com.raycoarana.awex.Task
import com.raycoarana.plaayer.common.tv.network.model.EpgChannel
import com.raycoarana.plaayer.common.tv.network.model.EpgProgramme
import com.raycoarana.plaayer.core.SimplePromise
import org.joda.time.DateTime
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParser.END_TAG
import org.xmlpull.v1.XmlPullParser.START_TAG
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.GZIPInputStream
import kotlin.collections.HashMap

class EpgDtoReader(
        private val awex: Awex,
        rawContent: InputStream
) {
    private val dateFormatter = SimpleDateFormat("yyyyMMddHHmmss Z", Locale.getDefault())
    private val pullParser: XmlPullParser = XmlPullParserFactory.newInstance().newPullParser()

    init {
        pullParser.setInput(GZIPInputStream(rawContent), null)
    }

    fun getNextChannel(): SimplePromise<EpgChannel> {
        return awex.submit(object : Task<EpgChannel, Unit>() {
            override fun run(): EpgChannel {
                while (pullParser.name != CHANNEL) {
                    pullParser.next()
                }

                val builder = EpgChannel.Builder()

                val attributesMap = pullParser.getAttributesAsMap()
                builder.withId(attributesMap[ID] ?: throw Exception("No id attribute found. ${debugPosition()}"))

                while (!(pullParser.name == CHANNEL && pullParser.eventType == END_TAG)) {
                    if (pullParser.eventType == START_TAG) {
                        when (pullParser.name) {
                            DISPLAY_NAME -> builder.withDisplayName(parseLabel())
                        }
                    }
                    pullParser.next()
                }

                // Skip END_TAG
                pullParser.next()

                return builder.build()
            }
        })
    }

    fun getNextProgramme(): SimplePromise<EpgProgramme> {
        return awex.submit(object : Task<EpgProgramme, Unit>() {
            override fun run(): EpgProgramme {
                while (pullParser.name != "programme") {
                    pullParser.next()
                }

                val builder = EpgProgramme.Builder()

                val attributesMap = pullParser.getAttributesAsMap()
                builder.withStart(DateTime(dateFormatter.parse(attributesMap["start"])))
                builder.withStop(DateTime(dateFormatter.parse(attributesMap["stop"])))
                builder.withChannelId(attributesMap["channel"]
                        ?: throw Exception("No channel attribute found. ${debugPosition()}"))

                while (!(pullParser.name == "programme" && pullParser.eventType == END_TAG)) {
                    if (pullParser.eventType == START_TAG) {
                        when (pullParser.name) {
                            TITLE -> builder.withTitle(parseLabel())
                            DESC -> builder.withDescription(parseLabel())
                            CATEGORY -> builder.addCategory(parseLabel())
                            ICON -> builder.withIcon(parseIcon())
                        }
                    }
                    pullParser.next()
                }

                // Skip END_TAG
                pullParser.next()

                return builder.build()
            }
        })
    }

    private fun parseIcon(): String {
        val attributes = pullParser.getAttributesAsMap()
        return attributes[SRC]
                ?: throw Exception("No src attribute found in icon. ${debugPosition()}")
    }

    private fun parseLabel(): Pair<String, String> {
        val attributes = pullParser.getAttributesAsMap()
        val lang = attributes[LANG]
                ?: throw Exception("No lang attribute found. ${debugPosition()}")
        val result = Pair(lang, pullParser.nextText())
        if (pullParser.eventType != END_TAG) {
            pullParser.next()
        }
        return result
    }

    private fun XmlPullParser.getAttributesAsMap(): Map<String, String> {
        val map = HashMap<String, String>()
        for (i in 0 until this.attributeCount) {
            val name = this.getAttributeName(i)
            val value = this.getAttributeValue(i)
            map[name] = value
        }
        return map
    }

    private fun debugPosition() = "${pullParser.lineNumber}:${pullParser.columnNumber}"

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val DESC = "desc"
        const val CATEGORY = "category"
        const val ICON = "icon"
        const val LANG = "lang"
        const val SRC = "src"
        const val CHANNEL = "channel"
        const val DISPLAY_NAME = "display-name"
    }
}

