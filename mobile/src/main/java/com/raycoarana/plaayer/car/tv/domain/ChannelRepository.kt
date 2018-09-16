package com.raycoarana.plaayer.car.tv.domain

import com.raycoarana.awex.Promise
import com.raycoarana.plaayer.car.tv.domain.model.Channel
import com.raycoarana.plaayer.core.ResourceProvider
import com.raycoarana.plaayer.core.di.ActivitySingleton
import javax.inject.Inject

@ActivitySingleton
class ChannelRepository @Inject constructor(
        private val definitionStorage: ChannelDefinitionStorage,
        private val resourceProvider: ResourceProvider
) {
    fun getChannelList(): Promise<List<Channel>, Void> = definitionStorage.getListDefinition()
            .mapSingle { listDefinition ->
                listDefinition.channels.map { channelDefinition ->
                    Channel(
                            channelDefinition.id,
                            channelDefinition.title,
                            channelDefinition.stream.url,
                            resourceProvider.getDrawableResId(channelDefinition.logo)
                    )
                }
            }
}