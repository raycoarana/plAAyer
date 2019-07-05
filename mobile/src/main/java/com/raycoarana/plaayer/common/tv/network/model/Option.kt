package com.raycoarana.plaayer.common.tv.network.model

data class Option(
        val format: String,
        val url: String,
        val extraInfo: List<String>
) {
    class Builder {
        private lateinit var format: String
        private lateinit var url: String
        private lateinit var extraInfo: List<String>

        fun withFormat(value: String) {
            format = value
        }

        fun withUrl(value: String) {
            url = value
        }

        fun withExtraInfo(values: List<String>) {
            extraInfo = values
        }

        fun build(): Option = Option(format, url, extraInfo)
    }
}