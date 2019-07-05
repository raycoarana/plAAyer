package com.raycoarana.plaayer.common.tv.network.model

import org.joda.time.DateTime
import java.util.ArrayList

class EpgProgramme(
        val channelId: String,
        val start: DateTime,
        val stop: DateTime,
        val categories: Map<String, List<String>>,
        val title: Map<String, String>,
        val description: Map<String, String>,
        val icon: String) {

    class Builder {
        private lateinit var channelId: String
        private lateinit var stop: DateTime
        private lateinit var start: DateTime
        private lateinit var iconSrc: String
        private val titleMap = HashMap<String, String>()
        private val descriptionMap = HashMap<String, String>()
        private val categoryMap = HashMap<String, List<String>>()

        fun build() = EpgProgramme(
                channelId,
                start,
                stop,
                categoryMap,
                titleMap,
                descriptionMap,
                iconSrc
        )

        fun withChannelId(value: String) = apply {
            channelId = value
        }

        fun withTitle(label: Pair<String, String>) = apply {
            titleMap[label.first] = label.second
        }

        fun withDescription(label: Pair<String, String>) = apply {
            descriptionMap[label.first] = label.second
        }

        fun withStart(value: DateTime) = apply {
            start = value
        }

        fun withStop(value: DateTime) = apply {
            stop = value
        }

        fun addCategory(label: Pair<String, String>) = apply {
            val list = categoryMap[label.first] ?: ArrayList()
            categoryMap[label.first] = list.plus(label.second)
        }

        fun withIcon(src: String) = apply {
            iconSrc = src
        }
    }
}