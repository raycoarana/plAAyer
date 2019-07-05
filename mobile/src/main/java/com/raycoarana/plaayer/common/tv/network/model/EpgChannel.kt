package com.raycoarana.plaayer.common.tv.network.model

class EpgChannel(
        val id: String,
        val displayName: Map<String, String>
) {
    class Builder {
        private lateinit var id: String
        private val displayName = HashMap<String, String>()

        fun build() = EpgChannel(
                id,
                displayName
        )

        fun withId(value: String) = apply {
            id = value
        }

        fun withDisplayName(value: Pair<String, String>) = apply {
            displayName[value.first] = value.second
        }
    }
}