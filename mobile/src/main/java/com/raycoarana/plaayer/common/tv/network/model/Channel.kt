package com.raycoarana.plaayer.common.tv.network.model

data class Channel(
        val name: String,
        val web: String,
        val logo: String,
        val resolution: String,
        val epgId: String?,
        val options: List<Option>,
        val extraInfo: List<String>,
        val category: String?,
        val country: String?
) {
    class Builder {

        private lateinit var name: String
        private lateinit var web: String
        private lateinit var logo: String
        private lateinit var resolution: String
        private lateinit var options: List<Option>
        private lateinit var extraInfo: List<String>
        private var epgId: String? = null
        private var category: String? = null
        private var country: String? = null

        fun withName(value: String) {
            name = value
        }

        fun withWeb(value: String) {
            web = value
        }

        fun withLogo(value: String) {
            logo = value
        }

        fun withResolution(value: String) {
            resolution = value
        }

        fun withEpgId(value: String?) {
            epgId = value
        }

        fun withOptions(values: List<Option>) {
            options = values
        }

        fun withExtraInfo(values: List<String>) {
            extraInfo = values
        }

        fun withCategory(value: String?) {
            category = value
        }

        fun withCountry(value: String?) {
            country = value
        }

        fun build(): Channel = Channel(
                name,
                web,
                logo,
                resolution,
                epgId,
                options,
                extraInfo,
                category,
                country
        )
    }
}