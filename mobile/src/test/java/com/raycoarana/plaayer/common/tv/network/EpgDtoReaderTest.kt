package com.raycoarana.plaayer.common.tv.network

import com.raycoarana.awex.FakeAwex
import com.raycoarana.plaayer.common.tv.network.reader.EpgDtoReader
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.junit.Assert.*
import org.junit.Test

class EpgDtoReaderTest {

    @Test
    fun readEpgFromFile() {
        val inputStream = javaClass.getResourceAsStream("/TDTChannels_EPG.xml.gz") ?: throw Exception("Resource not found.")

        val awex = FakeAwex()
        val reader = EpgDtoReader(awex, inputStream)

        val nextChannel = reader.getNextChannel().result
        assertEquals("La1.TDTChannelsEPG", nextChannel.id)
        assertEquals("La 1", nextChannel.displayName["es"])

        assertNotNull(reader.getNextChannel().result)

        val nextProgramme = reader.getNextProgramme().result
        assertEquals("La1.TDTChannelsEPG", nextProgramme.channelId)
        val cetTimeZone = DateTimeZone.forID("CET")
        assertTrue(DateTime(2019, 6, 29, 6, 0, 0, cetTimeZone).isEqual(nextProgramme.start))
        assertTrue(DateTime(2019, 6, 29, 9, 20, 0, cetTimeZone).isEqual(nextProgramme.stop))
        assertEquals("Noticias 24H", nextProgramme.title["es"])
        assertEquals("Noticias de los servicios informativos del Canal 24 Horas. Producido por Televisión Española, este canal está dedicado íntegramente a ofrecer información de actualidad y es el más antiguo de los canales de este tipo en España.", nextProgramme.description["es"])
        assertEquals(listOf("Información", "Informativo"), nextProgramme.categories["es"])
        assertEquals("http://www.movistarplus.es/recorte/n/caratula4/MTVEP518403", nextProgramme.icon)

        assertNotNull(reader.getNextProgramme().result)
    }
}