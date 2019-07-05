package com.raycoarana.plaayer.common.tv.network

import com.fasterxml.jackson.core.JsonFactory
import com.raycoarana.plaayer.common.tv.network.model.Option
import org.junit.Assert.assertEquals
import org.junit.Test

class ChannelListDtoReaderTest {

    @Test
    fun readChannelsFromFile() {
        val inputStream = javaClass.getResourceAsStream("/channels.json") ?: throw Exception("Resource not found.")

        val reader = ChannelListDtoReader(JsonFactory(), inputStream)

        val channel = reader.nextChannel() ?: throw Exception("Chungo!")

        assertEquals("La 1 HD", channel.name)
        assertEquals("http://www.rtve.es/directo/la-1/", channel.web)
        assertEquals("https://pbs.twimg.com/profile_images/899385012801470464/akSvNCqE_400x400.jpg", channel.logo)
        assertEquals("HD", channel.resolution)
        assertEquals("La1.TDTChannelsEPG", channel.epgId)
        assertEquals(listOf(
                Option("m3u8", "http://hlsliveamdgl1-lh.akamaihd.net/i/hlsdvrlive_1@584096/master.m3u8", listOf("GEO")),
                Option("m3u8", "http://hlsliveamdgl7-lh.akamaihd.net/i/hlsdvrlive_1@583042/master.m3u8", emptyList())
        ), channel.options)
        assertEquals(listOf("GEO"), channel.extraInfo)
        assertEquals("Spain", channel.country)
        assertEquals("Generalistas", channel.category)

        val channels = ArrayList(listOf(channel))
        do {
            val element = reader.nextChannel()
            element?.let {
                println(it.name)
                channels.add(it)
            }
        } while(element != null)

        assertEquals(425, channels.size)

        assertEquals(1562106367L, reader.getUpdatedTimestamp())
    }

}