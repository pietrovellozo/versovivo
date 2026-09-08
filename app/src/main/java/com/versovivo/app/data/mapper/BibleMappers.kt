package com.versovivo.app.data.mapper

import com.versovivo.app.data.remote.BookResponse
import com.versovivo.app.data.remote.ChapterResponse
import com.versovivo.app.domain.model.BibleBook
import com.versovivo.app.domain.model.BibleVerse

fun BookResponse.toDomain(): BibleBook = BibleBook(
    name = name,
    abbreviation = abbrev.pt,
    chapters = chapters
)

fun ChapterResponse.toDomain(book: BibleBook): List<BibleVerse> = verses.map { verse ->
    BibleVerse(
        book = book.name,
        chapter = chapter.number,
        number = verse.number,
        text = verse.text
    )
}