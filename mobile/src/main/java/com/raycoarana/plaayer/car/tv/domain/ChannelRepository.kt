package com.raycoarana.plaayer.car.tv.domain

import com.raycoarana.awex.Awex
import com.raycoarana.awex.Promise
import com.raycoarana.plaayer.car.tv.domain.model.Channel
import com.raycoarana.plaayer.car.tv.domain.model.Stream
import com.raycoarana.plaayer.core.ResourceProvider
import com.raycoarana.plaayer.core.di.ActivitySingleton
import javax.inject.Inject

@ActivitySingleton
class ChannelRepository @Inject constructor(
        private val definitionStorage: ChannelDefinitionStorage,
        private val resourceProvider: ResourceProvider,
        private val awex: Awex
) {
    private var channels: List<Channel>? = null

    fun getChannelList(): Promise<List<Channel>, Void> = definitionStorage.getListDefinition()
            .mapSingle { listDefinition ->
                listDefinition.channels.map { channelDefinition ->
                    Channel(
                            channelDefinition.id,
                            channelDefinition.title,
                            channelDefinition.streams.entries.map { Stream(it.key, it.value) },
                            resourceProvider.getDrawableResId(channelDefinition.logo)
                    )
                }
            }.done { channels = it }

    fun getChannel(id: Int): Promise<Channel, Void> {
        val channel = channels?.first { it.id == id }
        return if (channel !== null) {
            awex.of(channel)
        } else {
            getChannelList().stream<Channel>().filter { it.id == id }.singleOrFirst()
        }
    }
}
