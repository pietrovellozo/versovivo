package com.versovivo.app.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

// DTOs para a API abibliadigital
data class BookResponse(
    val abbrev: AbbrevResponse,
    val name: String,
    val chapters: Int,
    val group: String
)

data class AbbrevResponse(
    val pt: String,
    val en: String
)

data class ChapterResponse(
    val verses: List<VerseResponse>,
    val chapter: ChapterInfo
)

data class ChapterInfo(
    val number: Int,
    val verses: Int
)

data class VerseResponse(
    val number: Int,
    val text: String
)

interface BibleApiService {
    @GET("books")
    suspend fun getBooks(): List<BookResponse>

    @GET("verses/nvi/{abbrev}/{chapter}")
    suspend fun getChapterVerses(
        @Path("abbrev") abbrev: String,
        @Path("chapter") chapter: Int
    ): ChapterResponse
}
