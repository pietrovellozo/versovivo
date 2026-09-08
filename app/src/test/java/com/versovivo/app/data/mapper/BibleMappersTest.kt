package com.versovivo.app.data.mapper

import com.versovivo.app.data.remote.AbbrevResponse
import com.versovivo.app.data.remote.BookResponse
import com.versovivo.app.data.remote.ChapterInfo
import com.versovivo.app.data.remote.ChapterResponse
import com.versovivo.app.data.remote.VerseResponse
import com.versovivo.app.domain.model.BibleBook
import org.junit.Assert.assertEquals
import org.junit.Test

class BibleMappersTest {
    @Test
    fun `book response maps to domain book`() {
        val response = BookResponse(
            abbrev = AbbrevResponse(pt = "gn", en = "gen"),
            name = "Genesis",
            chapters = 50,
            group = "Pentateuco"
        )

        assertEquals(BibleBook("Genesis", "gn", 50), response.toDomain())
    }

    @Test
    fun `chapter response maps verses using chapter and book`() {
        val book = BibleBook("Genesis", "gn", 50)
        val response = ChapterResponse(
            verses = listOf(VerseResponse(number = 1, text = "No principio...")),
            chapter = ChapterInfo(number = 1, verses = 1)
        )

        val verse = response.toDomain(book).single()

        assertEquals("Genesis", verse.book)
        assertEquals(1, verse.chapter)
        assertEquals(1, verse.number)
        assertEquals("No principio...", verse.text)
    }
}