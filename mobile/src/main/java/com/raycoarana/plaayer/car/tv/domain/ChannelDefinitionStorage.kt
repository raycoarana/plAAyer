package com.raycoarana.plaayer.car.tv.domain

import com.raycoarana.awex.Awex
import com.raycoarana.awex.Promise
import com.raycoarana.awex.Task
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.tv.domain.model.ChannelListDefinition
import com.raycoarana.plaayer.core.ResourceProvider
import com.raycoarana.plaayer.core.json.Json
import javax.inject.Inject

class ChannelDefinitionStorage @Inject constructor(
        private val awex: Awex,
        private val json: Json,
        private val resourceProvider: ResourceProvider
) {
    fun getListDefinition(): Promise<ChannelListDefinition, Void> =
        awex.submit(object : Task<ChannelListDefinition, Void>() {
            override fun run(): ChannelListDefinition {
                val reader = resourceProvider.getReaderFromRaw(R.raw.channels)
                return json.fromJson(reader, ChannelListDefinition::class.java)
            }
        })
}