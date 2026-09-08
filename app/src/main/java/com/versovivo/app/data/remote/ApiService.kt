package com.versovivo.app.data.remote

import com.versovivo.app.domain.model.Verse
import retrofit2.http.GET

interface ApiService {
    @GET("/verse/of-day")
    suspend fun getVerseOfDay(): Verse
}
