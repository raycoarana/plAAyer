package com.raycoarana.plaayer.car.tv.domain.model

import com.google.gson.annotations.SerializedName

data class ChannelDefinition constructor(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("source") val source: String,
        @SerializedName("streams") val streams: LinkedHashMap<String, String>,
        @SerializedName("logo") val logo: String
)
