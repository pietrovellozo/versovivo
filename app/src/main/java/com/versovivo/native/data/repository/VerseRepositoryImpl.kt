package com.versovivo.native.data.repository

import com.versovivo.native.data.remote.ApiClient
import com.versovivo.native.domain.model.Verse
import com.versovivo.native.domain.repository.VerseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VerseRepositoryImpl : VerseRepository {
    private val api by lazy { ApiClient.create() }

    override suspend fun getVerseOfDay(): Verse = withContext(Dispatchers.IO) {
        try {
            api.getVerseOfDay()
        } catch (e: Exception) {
            Verse(
                reference = "Salmos 23:1",
                text = "O Senhor é o meu pastor; nada me faltará.",
                themes = listOf("devocional", "confiança")
            )
        }
    }
}
