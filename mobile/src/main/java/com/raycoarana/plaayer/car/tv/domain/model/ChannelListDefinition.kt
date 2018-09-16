package com.raycoarana.plaayer.car.tv.domain.model

import com.google.gson.annotations.SerializedName

data class ChannelListDefinition constructor(
        @SerializedName("channels") val channels: List<ChannelDefinition>
)
