package com.raycoarana.plaayer.car.tv.domain.model

import com.google.gson.annotations.SerializedName

data class ChannelDefinition constructor(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("source") val source: String,
        @SerializedName("stream") val stream: StreamDefinition,
        @SerializedName("logo") val logo: String
)

data class StreamDefinition constructor(
        @SerializedName("type") val type: String,
        @SerializedName("url") val url: String
)
