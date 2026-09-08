package com.versovivo.native.data.remote

import com.versovivo.native.domain.model.Verse
import retrofit2.http.GET

interface ApiService {
    @GET("/verse/of-day")
    suspend fun getVerseOfDay(): Verse
}
